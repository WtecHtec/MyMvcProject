package com.wf.ew.oauth.client;

import com.alibaba.fastjson.JSON;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.util.DefaultJdbcListFactory;
import org.springframework.security.oauth2.common.util.JdbcListFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by wangfan on 2018-12-11 下午 3:11.
 */
public class ClientDaoImpl implements ClientDao {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcListFactory listFactory;
    private final PasswordEncoder passwordEncoder;
    private RowMapper<Client> rowMapper = new ClientRowMapper();

    private static final String UPDATE_FIELDS = "resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove, client_name, raw_client_secret";

    private static final String CLIENT_FIELDS = "client_secret, " + UPDATE_FIELDS + ", client_id";

    private static final String BASE_SELECT = "select " + CLIENT_FIELDS + " from oauth_client_details";

    private static final String SQL_SELECT_ALL = BASE_SELECT + " order by client_id";

    private static final String SQL_SELECT_BY_ID = BASE_SELECT + " where client_id = ?";

    private static final String SQL_INSERT = "insert into oauth_client_details (" + CLIENT_FIELDS + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE = "update oauth_client_details set " + UPDATE_FIELDS.replaceAll(", ", "=?, ") + "=? where client_id = ?";

    private static final String SQL_UPDATE_SECRET = "update oauth_client_details set client_secret = ? where client_id = ?";

    private static final String SQL_DELETE = "delete from oauth_client_details where client_id = ?";

    public ClientDaoImpl(DataSource dataSource, PasswordEncoder passwordEncoder) {
        Assert.notNull(dataSource, "DataSource required");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.listFactory = new DefaultJdbcListFactory(new NamedParameterJdbcTemplate(jdbcTemplate));
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int save(ClientDetails client) {
        List<Object> objects = getFieldsForUpdate(client);
        objects.add(0, client.getClientSecret() != null ? passwordEncoder.encode(client.getClientSecret()) : null);
        return jdbcTemplate.update(SQL_INSERT, listToArray(objects));
    }

    @Override
    public Client findByClientId(String clientId) {
        Client client;
        try {
            client = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, clientId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return client;
    }

    @Override
    public int update(ClientDetails client) {
        int count = jdbcTemplate.update(SQL_UPDATE, listToArray(getFieldsForUpdate(client)));
        if (count <= 0) {
            throw new NoSuchClientException("No client found with id = " + client.getClientId());
        }
        return count;
    }

    @Override
    public List<Client> findAll() {
        return listFactory.getList(SQL_SELECT_ALL, Collections.emptyMap(), rowMapper);
    }

    @Override
    public int delete(String clientId) {
        int count = jdbcTemplate.update(SQL_DELETE, clientId);
        if (count <= 0) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
        return count;
    }

    private List<Object> getFieldsForUpdate(ClientDetails client) {
        List<Object> objects = new ArrayList();
        objects.add(client.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(client.getResourceIds()) : null);
        objects.add(client.getScope() != null ? StringUtils.collectionToCommaDelimitedString(client.getScope()) : null);
        objects.add(client.getAuthorizedGrantTypes() != null ? StringUtils.collectionToCommaDelimitedString(client.getAuthorizedGrantTypes()) : null);
        objects.add(client.getRegisteredRedirectUri() != null ? StringUtils.collectionToCommaDelimitedString(client.getRegisteredRedirectUri()) : null);
        objects.add(client.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(client.getAuthorities()) : null);
        objects.add(client.getAccessTokenValiditySeconds());
        objects.add(client.getRefreshTokenValiditySeconds());
        String json = null;
        try {
            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
            json = mapper.writeValueAsString(client.getAdditionalInformation());
        } catch (Exception e) {
            e.printStackTrace();
        }
        objects.add(json);
        String approveScopes;
        if (client.isAutoApprove("true")) {
            approveScopes = "true"; // all scopes autoapproved
        } else {
            Set<String> scopes = new HashSet();
            for (String scope : client.getScope()) {
                if (client.isAutoApprove(scope)) {
                    scopes.add(scope);
                }
            }
            approveScopes = StringUtils.collectionToCommaDelimitedString(scopes);
        }
        objects.add(approveScopes);
        objects.add(((Client) client).getClientName());
        objects.add(((Client) client).getRawClientSecret());
        objects.add(client.getClientId());
        return objects;
    }

    private Object[] listToArray(List<Object> list) {
        if (list == null) {
            return null;
        }
        Object[] objects = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objects[i] = list.get(i);
        }
        return objects;
    }

    private static class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            String clientId = rs.getString("client_id");
            String resourceIds = rs.getString("resource_ids");
            String scopes = rs.getString("scope");
            String grantTypes = rs.getString("authorized_grant_types");
            String authorities = rs.getString("authorities");
            String redirectUris = rs.getString("web_server_redirect_uri");
            Client client = new Client(clientId, resourceIds, scopes, grantTypes, authorities, redirectUris);
            String clientSecret = rs.getString("client_secret");
            client.setClientSecret(clientSecret);
            Object accessTokenValidity = rs.getObject("access_token_validity");
            if (accessTokenValidity != null) {
                client.setAccessTokenValiditySeconds((int) accessTokenValidity);
            }
            Object refreshTokenValidity = rs.getObject("refresh_token_validity");
            if (refreshTokenValidity != null) {
                client.setRefreshTokenValiditySeconds((int) refreshTokenValidity);
            }
            String additionalInformation = rs.getString("additional_information");
            if (additionalInformation != null) {
                try {
                    org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
                    client.setAdditionalInformation(mapper.readValue(additionalInformation, Map.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String autoapprove = rs.getString("autoapprove");
            if (autoapprove != null) {
                client.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(autoapprove));
            }
            String clientName = rs.getString("client_name");
            client.setClientName(clientName);
            String rawClientSecret = rs.getString("raw_client_secret");
            client.setRawClientSecret(rawClientSecret);
            return client;
        }
    }
}

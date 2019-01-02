package com.wf.ew.oauth.client;

import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.List;

/**
 * Created by wangfan on 2018-12-11 下午 3:11.
 */
public interface ClientDao {
    int save(ClientDetails client);

    Client findByClientId(String clientId);

    int update(ClientDetails client);

    List<Client> findAll();

    // List<T> findAll(int pageNo, int pageSize);

    int delete(String clientId);
}

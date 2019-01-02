package com.wf.ew.oauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by wangfan on 2018-12-11 下午 3:15.
 */
public class ClientService implements ClientDetailsService, ClientRegistrationService {
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientDao.findByClientId(clientId);
        if (client == null) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        return client;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        if (clientDao.findByClientId(clientDetails.getClientId()) != null) {
            throw new ClientAlreadyExistsException("The client already exists");
        }
        clientDao.save(clientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        if (clientDao.findByClientId(clientDetails.getClientId()) != null) {
            clientDao.update(clientDetails);
        } else {
            throw new NoSuchClientException("Not Found The Client.");
        }
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        Client client = clientDao.findByClientId(clientId);
        if (client != null) {
            client.setClientSecret(passwordEncoder.encode(secret));
            clientDao.update(client);
        } else {
            throw new NoSuchClientException("Not Found The Client.");
        }
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        clientDao.delete(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<ClientDetails> clientDetails = new ArrayList<>();
        clientDao.findAll().forEach(new Consumer<Client>() {
            @Override
            public void accept(Client client) {
                clientDetails.add(client);
            }
        });
        return clientDetails;
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public Client findByClientId(String clientId) {
        return clientDao.findByClientId(clientId);
    }
}

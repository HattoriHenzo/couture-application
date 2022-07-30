package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.repository.ClientRepository;
import com.imaginesoft.application.couture.generic.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericService<Client> {

    @Autowired
    public ClientService(ClientRepository repository) {
        super(repository);
    }
}

package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.repository.ClientRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends GenericService<Client> {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Client> getAll() throws RecordNotFoundException {
        List<Client> clients = repository.findAll();
        if(clients.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return clients;
    }

    @Override
    public Client createOrUpdate(Client client) {
        validateDomainRecord(client);
        return repository.save(client);
    }

    @Override
    public Client delete(Client client) {
        Client clientToDelete = repository.getById(client.getId());
        repository.delete(clientToDelete);
        return clientToDelete;
    }
}

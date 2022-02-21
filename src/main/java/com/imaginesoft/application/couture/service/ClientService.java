package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.repository.ClientRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ClientService extends GenericService<Client> {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {

        this.repository = repository;
    }

    @Override
    public Client findById(Long id) throws RecordNotFoundException {

        var client = repository.findById(id);
        return client.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<Client> findAll() throws RecordNotFoundException {

        var clients = repository.findAll();
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
        var clientToDelete = repository.findById(client.getId());
        AtomicReference<Client> deletedClient = new AtomicReference<>();

        clientToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedClient.set(clientToDelete.get());

        });

        return deletedClient.get();
    }
}

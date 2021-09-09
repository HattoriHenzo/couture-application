package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements GenericService<Client> {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client getById(Long id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public boolean create(Client object) {
        return false;
    }

    @Override
    public boolean update(Client object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

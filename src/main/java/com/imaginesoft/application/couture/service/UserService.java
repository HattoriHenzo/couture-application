package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.repository.LoginRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends GenericService<Login> {

    private LoginRepository repository;

    @Autowired
    public UserService(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public Login findById(Long id) {
        return null;
    }

    @Override
    public List<Login> findAll() {
        return null;
    }

    @Override
    public Login createOrUpdate(Login user) {
        return null;
    }

    @Override
    public Login delete(Login user) {
        return null;
    }
}

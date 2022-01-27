package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.User;
import com.imaginesoft.application.couture.repository.UserRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends GenericService<User> {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User createOrUpdate(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }
}

package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.repository.LoginRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LoginService extends GenericService<Login> {

    private LoginRepository repository;

    @Autowired
    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public Login findById(Long id) throws RecordNotFoundException {
        var login = repository.findById(id);
        return login.orElseThrow(
                () -> new RecordNotFoundException("No record found"));
    }

    @Override
    public List<Login> findAll() throws RecordNotFoundException {
        var logins = repository.findAll();
        if(logins.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return logins;
    }

    @Override
    public Login createOrUpdate(Login login) {
        validateDomainRecord(login);
        return repository.save(login);
    }

    @Override
    public Login delete(Login login) {
        var loginToDelete = repository.findById(login.getId());
        var deletedLogin = new AtomicReference<Login>();

        loginToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedLogin.set(value);
        });

        return deletedLogin.get();
    }
}

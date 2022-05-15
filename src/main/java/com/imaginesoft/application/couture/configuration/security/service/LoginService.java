package com.imaginesoft.application.couture.configuration.security.service;

import com.imaginesoft.application.couture.configuration.security.model.Login;
import com.imaginesoft.application.couture.configuration.security.repository.LoginRepository;
import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    private LoginRepository repository;

    private GenericService<Login> genericService;

    @Autowired
    public LoginService(LoginRepository repository) {
        this.repository = repository;
        this.genericService = new SimpleLoginService(this.repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Can't find the user %s", username)));
    }

    public Login findById(Long id) throws RecordNotFoundException {
        return genericService.findById(id);
    }

    public List<Login> findAll() throws RecordNotFoundException {
        return genericService.findAll();
    }

    public Login createOrUpdate(Login login) {
        return genericService.createOrUpdate(login);
    }

    public Login delete(Long id) {
        return genericService.delete(id);
    }
}

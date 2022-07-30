package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.repository.LoginRepository;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.service.exception.DomainRecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService extends GenericService<Login> implements UserDetailsService {

    private LoginRepository repository;

    private GenericService<Login> genericService;

    @Autowired
    public LoginService(LoginRepository repository) {
        super(repository);
        this.repository = repository;
        this.genericService = new SimpleLoginService(this.repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Can't find the user %s", username)));
    }

    @Override
    public Login findById(Long id) throws DomainRecordNotFoundException {
        return genericService.findById(id);
    }

    @Override
    public List<Login> findAll() throws DomainRecordNotFoundException {
        return genericService.findAll();
    }

    @Override
    public Login createOrUpdate(Login login) {
        return genericService.createOrUpdate(login);
    }

    @Override
    public Login delete(Long id) {
        return genericService.delete(id);
    }
}

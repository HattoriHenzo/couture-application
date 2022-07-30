package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends GenericService<Login> implements UserDetailsService {

    private LoginRepository repository;

    @Autowired
    public LoginService(LoginRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Can't find the user %s", username)));
    }
}

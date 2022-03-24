package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.repository.LoginRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends GenericService<Login> {

    @Autowired
    public LoginService(LoginRepository repository) {
        super(repository);
    }
}

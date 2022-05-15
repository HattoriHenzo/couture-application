package com.imaginesoft.application.couture.configuration.security.service;

import com.imaginesoft.application.couture.configuration.security.model.Login;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;

public class SimpleLoginService extends GenericService<Login> {

    protected SimpleLoginService(JpaRepository<Login, Long> repository) {
        super(repository);
    }
}

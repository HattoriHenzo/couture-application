package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.generic.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;

public class SimpleLoginService extends GenericService<Login> {

    protected SimpleLoginService(JpaRepository<Login, Long> repository) {
        super(repository);
    }
}

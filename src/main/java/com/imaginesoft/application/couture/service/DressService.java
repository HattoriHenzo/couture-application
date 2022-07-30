package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.repository.DressRepository;
import com.imaginesoft.application.couture.generic.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DressService extends GenericService<Dress> {

    @Autowired
    public DressService(DressRepository repository) {
        super(repository);
    }
}

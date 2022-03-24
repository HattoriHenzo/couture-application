package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.repository.DressTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DressTypeService extends GenericService<DressType> {

    @Autowired
    public DressTypeService(DressTypeRepository repository) {
        super(repository);
    }
}

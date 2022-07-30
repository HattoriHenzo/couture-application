package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.ModelType;
import com.imaginesoft.application.couture.repository.ModelTypeRepository;
import com.imaginesoft.application.couture.generic.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelTypeService extends GenericService<ModelType> {

    @Autowired
    public ModelTypeService(ModelTypeRepository repository) {
        super(repository);
    }
}

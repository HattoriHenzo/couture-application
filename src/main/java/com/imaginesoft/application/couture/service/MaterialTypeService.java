package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.repository.MaterialTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialTypeService extends GenericService<MaterialType> {

    @Autowired
    public MaterialTypeService(MaterialTypeRepository repository) {
        super(repository);
    }
}

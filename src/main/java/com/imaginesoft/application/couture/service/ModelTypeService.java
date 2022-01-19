package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.ModelType;
import com.imaginesoft.application.couture.repository.ModelTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelTypeService extends GenericService<ModelType> {

    private ModelTypeRepository repository;

    @Autowired
    public ModelTypeService(ModelTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ModelType getById(Long id) {
        return null;
    }

    @Override
    public List<ModelType> getAll() {
        return null;
    }

    @Override
    public ModelType createOrUpdate(ModelType object) {
        return null;
    }

    @Override
    public ModelType delete(ModelType modelType) {
        return null;
    }
}

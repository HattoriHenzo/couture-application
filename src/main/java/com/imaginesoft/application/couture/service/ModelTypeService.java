package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.model.ModelType;
import com.imaginesoft.application.couture.repository.ModelTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ModelTypeService extends GenericService<ModelType> {

    private ModelTypeRepository repository;

    @Autowired
    public ModelTypeService(ModelTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ModelType findById(Long id) throws RecordNotFoundException {
        var modelType = repository.findById(id);
        return modelType.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<ModelType> findAll() throws RecordNotFoundException {
        var modelTypes = repository.findAll();
        if(modelTypes.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return modelTypes;
    }

    @Override
    public ModelType createOrUpdate(ModelType modelType) {
        validateDomainRecord(modelType);
        return repository.save(modelType);
    }

    @Override
    public ModelType delete(ModelType modelType) {
        var modelTypeToDelete = repository.findById(modelType.getId());
        AtomicReference<ModelType> deletedModelType = new AtomicReference<>();

        modelTypeToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedModelType.set(value);
        });
        return deletedModelType.get();
    }
}

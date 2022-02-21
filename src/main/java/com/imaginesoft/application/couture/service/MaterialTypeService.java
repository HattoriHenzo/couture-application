package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.repository.MaterialTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MaterialTypeService extends GenericService<MaterialType> {

    private MaterialTypeRepository repository;

    @Autowired
    public MaterialTypeService(MaterialTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MaterialType findById(Long id) throws RecordNotFoundException {
        var modelType = repository.findById(id);
        return modelType.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<MaterialType> findAll() throws RecordNotFoundException {
        var materialTypes = repository.findAll();
        if(materialTypes.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return materialTypes;
    }

    @Override
    public MaterialType createOrUpdate(MaterialType materialType) {
        validateDomainRecord(materialType);
        return repository.save(materialType);
    }

    @Override
    public MaterialType delete(MaterialType materialType) {
        var materialTypeToDelete = repository.findById(materialType.getId());
        var deletedMaterialType = new AtomicReference<MaterialType>();

        materialTypeToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedMaterialType.set(value);
        });

        return deletedMaterialType.get();
    }
}

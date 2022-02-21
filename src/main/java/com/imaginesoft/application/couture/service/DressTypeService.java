package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.repository.DressTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DressTypeService extends GenericService<DressType> {

    private DressTypeRepository repository;

    @Autowired
    public DressTypeService(DressTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public DressType findById(Long id) throws RecordNotFoundException {
        var dressType = repository.findById(id);
        return dressType.orElseThrow(
                () -> new RecordNotFoundException("No record found"));
    }

    @Override
    public List<DressType> findAll() throws RecordNotFoundException {
        var dressTypes = repository.findAll();
        if(dressTypes.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return dressTypes;
    }

    @Override
    public DressType createOrUpdate(DressType dressType) {
        validateDomainRecord(dressType);
        return repository.save(dressType);
    }

    @Override
    public DressType delete(DressType dressType) {
        var dressTypeToDelete = repository.findById(dressType.getId());
        AtomicReference<DressType> deletedDressType = new AtomicReference<>();

        dressTypeToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedDressType.set(value);
        });

        return deletedDressType.get();
    }
}

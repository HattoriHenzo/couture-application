package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.repository.DressRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressTypeService extends GenericService<DressType> {

    private DressRepository repository;

    @Autowired
    public DressTypeService(DressRepository repository) {
        this.repository = repository;
    }

    @Override
    public DressType getById(Long id) {
        return null;
    }

    @Override
    public List<DressType> getAll() {
        return null;
    }

    @Override
    public DressType createOrUpdate(DressType object) {
        return null;
    }

    @Override
    public DressType delete(DressType dressType) {
        return null;
    }
}

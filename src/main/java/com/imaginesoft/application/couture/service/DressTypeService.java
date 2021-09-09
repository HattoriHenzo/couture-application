package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.repository.DressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressTypeService implements GenericService<DressType> {

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
    public boolean create(DressType object) {
        return false;
    }

    @Override
    public boolean update(DressType object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.repository.MaterialTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialTypeService implements GenericService<MaterialType> {

    private MaterialTypeRepository repository;

    @Autowired
    public MaterialTypeService(MaterialTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MaterialType getById(Long id) {
        return null;
    }

    @Override
    public List<MaterialType> getAll() {
        return null;
    }

    @Override
    public boolean create(MaterialType object) {
        return false;
    }

    @Override
    public boolean update(MaterialType object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

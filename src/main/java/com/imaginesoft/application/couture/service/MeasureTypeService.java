package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.repository.MeasureTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureTypeService extends GenericService<MeasureType> {

    private MeasureTypeRepository repository;

    @Autowired
    public MeasureTypeService(MeasureTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MeasureType getById(Long id) {
        return null;
    }

    @Override
    public List<MeasureType> getAll() {
        return null;
    }

    @Override
    public MeasureType createOrUpdate(MeasureType measureType) {
        return null;
    }

    @Override
    public MeasureType delete(MeasureType measureType) {
        return null;
    }
}

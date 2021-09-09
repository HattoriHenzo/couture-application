package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureService implements GenericService<Measure> {

    private MeasureRepository repository;

    @Autowired
    public MeasureService(MeasureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Measure getById(Long id) {
        return null;
    }

    @Override
    public List<Measure> getAll() {
        return null;
    }

    @Override
    public boolean create(Measure object) {
        return false;
    }

    @Override
    public boolean update(Measure object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

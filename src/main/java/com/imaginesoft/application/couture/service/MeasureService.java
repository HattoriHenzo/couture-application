package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.repository.MeasureRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureService extends GenericService<Measure> {

    private MeasureRepository repository;

    @Autowired
    public MeasureService(MeasureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Measure findById(Long id) {
        return null;
    }

    @Override
    public List<Measure> findAll() {
        return null;
    }

    @Override
    public Measure createOrUpdate(Measure measure) {
        return null;
    }

    @Override
    public Measure delete(Measure measure) {
        return null;
    }
}

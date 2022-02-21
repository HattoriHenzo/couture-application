package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.repository.MeasureRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MeasureService extends GenericService<Measure> {

    private MeasureRepository repository;

    @Autowired
    public MeasureService(MeasureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Measure findById(Long id) throws RecordNotFoundException {
        var modelType = repository.findById(id);
        return modelType.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<Measure> findAll() throws RecordNotFoundException {
        var measures = repository.findAll();
        if(measures.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return measures;
    }

    @Override
    public Measure createOrUpdate(Measure measure) {
        validateDomainRecord(measure);
        return repository.save(measure);
    }

    @Override
    public Measure delete(Measure measure) {
        var measureToDelete = repository.findById(measure.getId());
        AtomicReference<Measure> deletedMeasure = new AtomicReference<>();

        measureToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedMeasure.set(value);
        });

        return deletedMeasure.get();
    }
}

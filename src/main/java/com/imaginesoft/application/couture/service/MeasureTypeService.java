package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.repository.MeasureTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MeasureTypeService extends GenericService<MeasureType> {

    private MeasureTypeRepository repository;

    @Autowired
    public MeasureTypeService(MeasureTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MeasureType findById(Long id) throws RecordNotFoundException {

        Optional<MeasureType> measureType = repository.findById(id);
        return measureType.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<MeasureType> findAll() throws RecordNotFoundException {
        List<MeasureType> measureTypes = repository.findAll();
        if(measureTypes.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return measureTypes;
    }

    @Override
    public MeasureType createOrUpdate(MeasureType measureType) {
        validateDomainRecord(measureType);
        return repository.save(measureType);
    }

    @Override
    public MeasureType delete(MeasureType measureType) {
        var measureTypeToDelete = repository.findById(measureType.getId());
        var deletedMeasureType = new AtomicReference<MeasureType>();

        measureTypeToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedMeasureType.set(value);
        });
        return deletedMeasureType.get();
    }
}

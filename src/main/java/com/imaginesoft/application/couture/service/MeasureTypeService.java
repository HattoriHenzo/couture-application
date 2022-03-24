package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.repository.MeasureTypeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureTypeService extends GenericService<MeasureType> {

    @Autowired
    public MeasureTypeService(MeasureTypeRepository repository) {
        super(repository);
    }
}

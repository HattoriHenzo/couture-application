package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.repository.MeasureRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService extends GenericService<Measure> {

    @Autowired
    public MeasureService(MeasureRepository repository) {
        super(repository);
    }
}

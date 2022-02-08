package com.imaginesoft.application.couture.controller.generic;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.http.ResponseEntity;

public abstract class GenericController<R, D, S> {

    protected GenericService<S> service;
    protected MapperWrapper mapper;
    protected DateTimeWrapper dateTime;

    protected GenericController(GenericService<S> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        this.service = service;
        this.mapper = mapper;
        this.dateTime = dateTime;
    }

    protected abstract ResponseEntity<R> findById(Long id) throws RecordNotFoundException;
    protected abstract ResponseEntity<R> findAll() throws RecordNotFoundException;
    protected abstract ResponseEntity<R> create(D object);
    protected abstract ResponseEntity<R> update(D object);
    protected abstract ResponseEntity<R> delete(Long id) throws RecordNotFoundException;

}

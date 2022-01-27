package com.imaginesoft.application.couture.controller.generic;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class GenericController<M, P> {

    public abstract ResponseEntity<M> getById(Long id);
    public abstract ResponseEntity<M> getAll() throws RecordNotFoundException;
    public abstract ResponseEntity<M> create(@RequestBody P object);
    public abstract ResponseEntity<M> update(@RequestBody P object);
    public abstract ResponseEntity<M> delete(Long id) throws RecordNotFoundException;

}

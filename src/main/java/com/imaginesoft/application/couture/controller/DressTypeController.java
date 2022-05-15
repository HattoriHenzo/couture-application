package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class DressTypeController extends GenericController<DressTypeDto, DressType> {

    @Autowired
    public DressTypeController(GenericService<DressType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, DressTypeDto.class, DressType.class);
    }

    @Override
    @GetMapping("/dress-types/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/dress-types")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/dress-types")
    protected ResponseEntity<Response> create(@Valid @RequestBody DressTypeDto dressTypeDto) {
        return super.create(dressTypeDto);
    }

    @Override
    @PutMapping("/dress-types")
    protected ResponseEntity<Response> update(@Valid @RequestBody DressTypeDto dressTypeDto) {
        return super.update(dressTypeDto);
    }

    @Override
    @DeleteMapping("/dress-types/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}

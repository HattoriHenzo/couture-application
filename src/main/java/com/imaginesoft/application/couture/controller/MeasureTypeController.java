package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class MeasureTypeController extends GenericController<MeasureTypeDto, MeasureType> {

    @Autowired
    protected MeasureTypeController(GenericService<MeasureType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, MeasureTypeDto.class, MeasureType.class);
    }

    @Override
    @GetMapping("/measure-types/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/measure-types")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/measure-types")
    protected ResponseEntity<Response> create(@RequestBody MeasureTypeDto measureTypeDto) {
        return super.create(measureTypeDto);
    }

    @Override
    @PutMapping("/measure-types")
    protected ResponseEntity<Response> update(@RequestBody MeasureTypeDto measureTypeDto) {
        return super.update(measureTypeDto);
    }

    @Override
    @DeleteMapping("/measure-types/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}

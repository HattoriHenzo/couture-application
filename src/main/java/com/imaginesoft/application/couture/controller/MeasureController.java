package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.MeasureDto;
import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class MeasureController extends GenericController<MeasureDto, Measure> {

    @Autowired
    protected MeasureController(GenericService<Measure> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, MeasureDto.class, Measure.class);
    }

    @Override
    @GetMapping("/measures/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/measures")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/measures")
    protected ResponseEntity<Response> create(@RequestBody MeasureDto measureDto) throws BadRequestException {
        return super.create(measureDto);
    }

    @Override
    @PutMapping("/measures")
    protected ResponseEntity<Response> update(@RequestBody MeasureDto measureDto) throws BadRequestException {
        return super.update(measureDto);
    }

    @Override
    @DeleteMapping("/measures/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

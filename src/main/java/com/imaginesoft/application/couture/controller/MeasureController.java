package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.MeasureDto;
import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1)
public class MeasureController extends GenericController<MeasureDto, Measure> {

    @Autowired
    protected MeasureController(GenericService<Measure> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, MeasureDto.class, Measure.class);
    }

    @Override
    @GetMapping("/measures/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/measures")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/measures")
    protected ResponseEntity<Response> create(@RequestBody MeasureDto measureDto) {
        return super.create(measureDto);
    }

    @Override
    @PutMapping("/measures")
    protected ResponseEntity<Response> update(@RequestBody MeasureDto measureDto) {
        return super.update(measureDto);
    }

    @Override
    @DeleteMapping("/measures/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}

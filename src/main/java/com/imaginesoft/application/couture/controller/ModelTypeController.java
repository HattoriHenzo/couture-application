package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.model.ModelType;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class ModelTypeController extends GenericController<ModelTypeDto, ModelType> {

    @Autowired
    public ModelTypeController(GenericService<ModelType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, ModelTypeDto.class, ModelType.class);
    }

    @Override
    @GetMapping("/model-types/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/model-types")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/model-types")
    protected ResponseEntity<Response> create(@RequestBody ModelTypeDto modelTypeDto) throws BadRequestException {
        return super.create(modelTypeDto);
    }

    @Override
    @PutMapping("/model-types")
    protected ResponseEntity<Response> update(@RequestBody ModelTypeDto modelTypeDto) throws BadRequestException {
        return super.update(modelTypeDto);
    }

    @Override
    @DeleteMapping("/model-types/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

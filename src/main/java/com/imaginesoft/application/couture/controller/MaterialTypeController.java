package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.MaterialTypeDto;
import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class MaterialTypeController extends GenericController<MaterialTypeDto, MaterialType> {

    @Autowired
    protected MaterialTypeController(GenericService<MaterialType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, MaterialTypeDto.class, MaterialType.class);
    }

    @Override
    @GetMapping("/material-types/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/material-types")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/material-types")
    protected ResponseEntity<Response> create(@RequestBody MaterialTypeDto materialTypeDto) throws BadRequestException {
        return super.create(materialTypeDto);
    }

    @Override
    @PutMapping("/material-types")
    protected ResponseEntity<Response> update(@RequestBody MaterialTypeDto materialTypeDto) throws BadRequestException {
        return super.update(materialTypeDto);
    }

    @Override
    @DeleteMapping("/material-types/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

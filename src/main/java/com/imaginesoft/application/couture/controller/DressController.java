package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.DressDto;
import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class DressController extends GenericController<DressDto, Dress> {

    @Autowired
    public DressController(GenericService<Dress> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, DressDto.class, Dress.class);
    }

    @Override
    @GetMapping("/dresses/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/dresses")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/dresses")
    protected ResponseEntity<Response> create(@Valid @RequestBody DressDto dressDto) throws BadRequestException {
        return super.create(dressDto);
    }

    @Override
    @PutMapping("/dresses")
    protected ResponseEntity<Response> update(@Valid @RequestBody DressDto dressDto) throws BadRequestException {
        return super.update(dressDto);
    }

    @Override
    @DeleteMapping("/dresses/{id}")
    protected ResponseEntity<Response> delete(@PathVariable Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

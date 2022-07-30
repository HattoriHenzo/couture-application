package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.LoginDto;
import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_ADMIN)
public class LoginController extends GenericController<LoginDto, Login> {

    @Autowired
    protected LoginController(GenericService<Login> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, LoginDto.class, Login.class);
    }

    @Override
    @GetMapping("/logins/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/logins")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/logins")
    protected ResponseEntity<Response> create(@Valid @RequestBody LoginDto loginDto) throws BadRequestException {
        return super.create(loginDto);
    }

    @Override
    @PutMapping("/logins")
    protected ResponseEntity<Response> update(@Valid @RequestBody LoginDto loginDto) throws BadRequestException {
        return super.update(loginDto);
    }

    @Override
    @DeleteMapping("/logins/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

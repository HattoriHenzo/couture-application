package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.LoginDto;
import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1)
public class LoginController extends GenericController<LoginDto, Login> {

    @Autowired
    protected LoginController(GenericService<Login> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, LoginDto.class, Login.class);
    }

    @Override
    @GetMapping("/logins/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/logins")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/logins")
    protected ResponseEntity<Response> create(@RequestBody LoginDto login) {
        return super.create(login);
    }

    @Override
    @PutMapping("/logins")
    protected ResponseEntity<Response> update(@RequestBody LoginDto login) {
        return super.update(login);
    }

    @Override
    @DeleteMapping("/logins/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}

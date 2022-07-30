package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
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
public class ClientController extends GenericController<ClientDto, Client> {

    @Autowired
    public ClientController(GenericService<Client> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, ClientDto.class, Client.class);
    }

    @Override
    @GetMapping("/clients/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/clients")
    public ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/clients")
    public ResponseEntity<Response> create(@Valid @RequestBody ClientDto clientDto) throws BadRequestException {
        return super.create(clientDto);
    }

    @Override
    @PutMapping("/clients")
    public ResponseEntity<Response> update(@Valid @RequestBody ClientDto clientDto) throws BadRequestException {
        return super.update(clientDto);
    }

    @Override
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

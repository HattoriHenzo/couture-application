package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
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
public class ClientController extends GenericController<ClientDto, Client> {

    @Autowired
    public ClientController(GenericService<Client> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, ClientDto.class, Client.class);
    }

    @Override
    @GetMapping("/clients/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/clients")
    public ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/clients")
    public ResponseEntity<Response> create(@Valid @RequestBody ClientDto clientDto) {
        return super.create(clientDto);
    }

    @Override
    @PutMapping("/clients")
    public ResponseEntity<Response> update(@Valid @RequestBody ClientDto clientDto) {
        return super.update(clientDto);
    }

    @Override
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}

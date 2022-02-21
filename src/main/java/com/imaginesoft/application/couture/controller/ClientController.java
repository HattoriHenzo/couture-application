package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1)
public class ClientController extends GenericController<ClientDto, Client> {

    @Autowired
    public ClientController(GenericService<Client> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/clients/{id}")
    public ResponseEntity<Success> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return findById(id);
    }

    @Override
    @GetMapping("/clients")
    public ResponseEntity<Success> findAll() throws RecordNotFoundException {
        return findAll();
    }

    @Override
    @PostMapping("/clients")
    public ResponseEntity<Success> create(@RequestBody ClientDto clientDto) {
        return create(clientDto);
    }

    @Override
    @PutMapping("/clients")
    public ResponseEntity<Success> update(@RequestBody ClientDto clientDto) {
        return update(clientDto);
    }

    @Override
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Success> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return delete(id);
    }
}

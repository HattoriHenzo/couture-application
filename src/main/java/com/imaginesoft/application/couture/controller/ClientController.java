package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.DataFactory;
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
@RequestMapping(value = DataFactory.API_V1)
public class ClientController extends GenericController<Success, ClientDto, Client> {

    @Autowired
    public ClientController(GenericService<Client> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/clients/{id}")
    public ResponseEntity<Success> findById(@PathVariable("id") Long id) throws RecordNotFoundException {

        var client = service.findById(id);
        var clientResponse = mapper.performMapping(client, ClientDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the client with id %d", id),
                clientResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @GetMapping("/clients")
    public ResponseEntity<Success> findAll() throws RecordNotFoundException {

        var clients = service.findAll();
        var clientsDtoResponse = clients.stream()
                .map(client -> mapper.performMapping(client, ClientDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "Get the list of all the clients",
                clientsDtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PostMapping("/clients")
    public ResponseEntity<Success> create(@RequestBody ClientDto clientDto) {

        var clientToCreate = mapper.performMapping(clientDto, Client.class);
        var createdClient = service.createOrUpdate(clientToCreate);
        var clientResponse = mapper.performMapping(createdClient, ClientDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A new client has been created",
                clientResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PutMapping("/clients")
    public ResponseEntity<Success> update(@RequestBody ClientDto clientDto) {

        var clientToUpdate = mapper.performMapping(clientDto, Client.class);
        var updatedClient = service.createOrUpdate(clientToUpdate);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A client has been updated",
                updatedClient);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Success> delete(@PathVariable("id") Long id) throws RecordNotFoundException {

        var clientToDelete = service.findById(id);

        if(Objects.isNull(clientToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The client with id %d is not found", id)
            );
        }

        var deletedClient = service.delete(clientToDelete);
        var clientResponse = mapper.performMapping(deletedClient, ClientDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A client has been deleted",
                clientResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

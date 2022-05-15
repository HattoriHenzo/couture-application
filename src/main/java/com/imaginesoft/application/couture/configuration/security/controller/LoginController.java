package com.imaginesoft.application.couture.configuration.security.controller;

import com.imaginesoft.application.couture.configuration.security.model.Login;
import com.imaginesoft.application.couture.configuration.security.service.LoginService;
import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.configuration.security.dto.LoginDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Clock;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_ADMIN)
public class LoginController {

    private LoginService service;
    private MapperWrapper mapper;
    private DateTimeWrapper dateTime;

    @Autowired
    protected LoginController(LoginService service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        this.service = service;
        this.mapper = mapper;
        this.dateTime = dateTime;
    }

    @GetMapping("/logins/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        var model = service.findById(id);
        var dtoResponse = mapper.performMapping(model, LoginDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the %s with id %d", Login.class.getSimpleName(), id),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @GetMapping("/logins")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        var models = service.findAll();
        var dtosResponse = models.stream()
                .map(model -> mapper.performMapping(model, LoginDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the list of all the %s", Login.class.getSimpleName()),
                dtosResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @PostMapping("/logins")
    protected ResponseEntity<Response> create(@Valid @RequestBody LoginDto dto) {
        var modelToCreate = mapper.performMapping(dto, Login.class);
        var createdModel = service.createOrUpdate(modelToCreate);
        var dtoResponse = mapper.performMapping(createdModel, LoginDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A new %s has been created", Login.class.getSimpleName()),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @PutMapping("/logins")
    protected ResponseEntity<Response> update(@Valid @RequestBody LoginDto dto) {
        var modelToUpdate = mapper.performMapping(dto, Login.class);
        var updatedModel = service.createOrUpdate(Login.class.cast(modelToUpdate));
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A %s has been updated", Login.class.getSimpleName()),
                updatedModel);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @DeleteMapping("/logins/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        var modelToDelete = service.findById(id);
        if(Objects.isNull(modelToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The %s with id %d is not found", Login.class.getSimpleName(), id)
            );
        }

        var deletedModel = service.delete(id);
        var dtoResponse = mapper.performMapping(deletedModel, LoginDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A %s has been deleted", Login.class.getSimpleName()),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

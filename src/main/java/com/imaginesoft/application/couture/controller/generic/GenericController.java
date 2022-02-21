package com.imaginesoft.application.couture.controller.generic;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class GenericController<D, M> {

    protected GenericService<M> service;
    protected MapperWrapper mapper;
    protected DateTimeWrapper dateTime;
    protected Class<D> DTO;
    protected Class<M> MODEL;

    protected GenericController(GenericService<M> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        this.service = service;
        this.mapper = mapper;
        this.dateTime = dateTime;
    }

    protected ResponseEntity<? extends Response> findById(Long id) throws RecordNotFoundException {
        var model = service.findById(id);
        var dtoResponse = mapper.performMapping(model, DTO.getClass());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the %s with id %d", MODEL.getSimpleName(), id),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    protected ResponseEntity<? extends Response> findAll() throws RecordNotFoundException {
        var models = service.findAll();
        var dtosResponse = models.stream()
                .map(model -> mapper.performMapping(model, DTO.getClass()))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the list of all the %s", MODEL.getSimpleName()),
                dtosResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
    protected ResponseEntity<? extends Response> create(D dto) {
        var modelToCreate = mapper.performMapping(dto, MODEL.getClass());
        var createdModel = service.createOrUpdate(MODEL.cast(modelToCreate));
        var dtoResponse = mapper.performMapping(createdModel, DTO.getClass());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A new %s has been created", MODEL.getSimpleName()),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
    protected ResponseEntity<? extends Response> update(D dto) {
        var modelToUpdate = mapper.performMapping(dto, MODEL.getClass());
        var updatedModel = service.createOrUpdate(MODEL.cast(modelToUpdate));
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A %s has been updated", MODEL.getSimpleName()),
                updatedModel);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    protected ResponseEntity<? extends Response> delete(Long id) throws RecordNotFoundException {
        var modelToDelete = service.findById(id);
        if(Objects.isNull(modelToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The client with id %d is not found", id)
            );
        }

        var deletedModel = service.delete(modelToDelete);
        var dtoResponse = mapper.performMapping(deletedModel, DTO.getClass());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A %s has been deleted", MODEL.getSimpleName()),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

}

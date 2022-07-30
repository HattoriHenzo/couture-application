package com.imaginesoft.application.couture.generic.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.service.exception.DomainRecordNotFoundException;
import com.imaginesoft.application.couture.service.exception.DomainRulesException;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.util.stream.Collectors;

public abstract class GenericController<D, M> {

    protected GenericService<M> service;
    protected MapperWrapper mapper;
    protected DateTimeWrapper dateTime;
    protected Class<D> dtoClass;
    protected Class<M> modelClass;

    protected GenericController(GenericService<M> service, MapperWrapper mapper, DateTimeWrapper dateTime, Class<D> dtoClass, Class<M> modelClass) {
        this.service = service;
        this.mapper = mapper;
        this.dateTime = dateTime;
        this.dtoClass = dtoClass;
        this.modelClass = modelClass;
    }

    protected ResponseEntity<Response> findById(Long id) throws ResourceNotFoundException {
        try {
            var model = service.findById(id);
            var dtoResponse = mapper.performMapping(model, dtoClass);
            var success = new Success(HttpStatus.OK,
                    dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                    String.format("Get the %s with id %d", modelClass.getSimpleName(), id),
                    dtoResponse);

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch(DomainRecordNotFoundException exception) {
            throw new ResourceNotFoundException("Resource not found", exception);
        }
    }

    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        try {
            var models = service.findAll();
            var dtosResponse = models.stream()
                    .map(model -> mapper.performMapping(model, dtoClass))
                    .collect(Collectors.toList());
            var success = new Success(HttpStatus.OK,
                    dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                    String.format("Get the list of all the %s", modelClass.getSimpleName()),
                    dtosResponse);

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResourceNotFoundException("Resources not found", exception);
        }
    }

    protected ResponseEntity<Response> create(D dto) throws BadRequestException {
        try {
            var modelToCreate = mapper.performMapping(dto, modelClass);
            var createdModel = service.createOrUpdate(modelToCreate);
            var dtoResponse = mapper.performMapping(createdModel, dtoClass);
            var success = new Success(HttpStatus.OK,
                    dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                    String.format("A new %s has been created", modelClass.getSimpleName()),
                    dtoResponse);

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (DomainRulesException exception) {
            throw new BadRequestException("Error while creating resource", exception);
        }
    }

    protected ResponseEntity<Response> update(D dto) throws BadRequestException {
        try {
            var modelToUpdate = mapper.performMapping(dto, modelClass);
            var updatedModel = service.createOrUpdate(modelClass.cast(modelToUpdate));
            var success = new Success(HttpStatus.OK,
                    dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                    String.format("A %s has been updated", modelClass.getSimpleName()),
                    updatedModel);

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (DomainRulesException exception) {
            throw new BadRequestException("Error while updating resource", exception);
        }
    }

    protected ResponseEntity<Response> delete(Long id) throws ResourceNotFoundException {
        try {
            var deletedModel = service.delete(id);
            var dtoResponse = mapper.performMapping(deletedModel, dtoClass);
            var success = new Success(HttpStatus.OK,
                    dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                    String.format("A %s has been deleted", modelClass.getSimpleName()),
                    dtoResponse);

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResourceNotFoundException("Resource not found", exception);
        }
    }
}

package com.imaginesoft.application.couture.controller.generic;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.controller.message.Success;
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
    protected Class<D> dtoClass;
    protected Class<M> modelClass;

    protected GenericController(GenericService<M> service, MapperWrapper mapper, DateTimeWrapper dateTime, Class<D> dtoClass, Class<M> modelClass) {
        this.service = service;
        this.mapper = mapper;
        this.dateTime = dateTime;
        this.dtoClass = dtoClass;
        this.modelClass = modelClass;
    }

    protected ResponseEntity<Response> findById(Long id) throws RecordNotFoundException {
        var model = service.findById(id);
        var dtoResponse = mapper.performMapping(model, dtoClass);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the %s with id %d", modelClass.getSimpleName(), id),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        var models = service.findAll();
        var dtosResponse = models.stream()
                .map(model -> mapper.performMapping(model, dtoClass))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the list of all the %s", modelClass.getSimpleName()),
                dtosResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    protected ResponseEntity<Response> create(D dto) {
        var modelToCreate = mapper.performMapping(dto, modelClass);
        var createdModel = service.createOrUpdate(modelToCreate);
        var dtoResponse = mapper.performMapping(createdModel, dtoClass);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A new %s has been created", modelClass.getSimpleName()),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    protected ResponseEntity<Response> update(D dto) {
        var modelToUpdate = mapper.performMapping(dto, modelClass);
        var updatedModel = service.createOrUpdate(modelClass.cast(modelToUpdate));
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A %s has been updated", modelClass.getSimpleName()),
                updatedModel);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    protected ResponseEntity<Response> delete(Long id) throws RecordNotFoundException {
        var modelToDelete = service.findById(id);
        if(Objects.isNull(modelToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The %s with id %d is not found", modelClass.getSimpleName(), id)
            );
        }

        var deletedModel = service.delete(id);
        var dtoResponse = mapper.performMapping(deletedModel, dtoClass);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("A %s has been deleted", modelClass.getSimpleName()),
                dtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

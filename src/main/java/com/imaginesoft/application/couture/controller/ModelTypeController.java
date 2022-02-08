package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.model.ModelType;
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
public class ModelTypeController extends GenericController<Success, ModelTypeDto, ModelType> {

    private static Long ID = 1L;
    private static String NAME = "SHERPA";
    private static String UPDATED_NAME = "UPDATED_NAME";

    @Autowired
    public ModelTypeController(GenericService<ModelType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/model-types/{id}")
    protected ResponseEntity<Success> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        
        var modelType = service.findById(id);
        var modelTypeResponse = mapper.performMapping(modelType, ModelTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the model type with id %d", id),
                modelTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @GetMapping("/model-types")
    protected ResponseEntity<Success> findAll() throws RecordNotFoundException {

        var modelTypes = service.findAll();
        var modelTypesDtoResponse = modelTypes.stream()
                .map(modelType -> mapper.performMapping(modelType, ModelTypeDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "Get the list of all the model type",
                modelTypesDtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PostMapping("/model-types")
    protected ResponseEntity<Success> create(@RequestBody ModelTypeDto modelTypeDto) {

        var modelTypeToCreate = mapper.performMapping(modelTypeDto, ModelType.class);
        var createdModelType = service.createOrUpdate(modelTypeToCreate);
        var modelTypeResponse = mapper.performMapping(createdModelType, ModelTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A new model type has been created",
                modelTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PutMapping("/model-types")
    protected ResponseEntity<Success> update(@RequestBody ModelTypeDto modelTypeDto) {

        var modelTypeToUpdate = mapper.performMapping(modelTypeDto, ModelType.class);
        var updatedModelType = service.createOrUpdate(modelTypeToUpdate);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A model type has been updated",
                updatedModelType);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @DeleteMapping("/model-types/{id}")
    protected ResponseEntity<Success> delete(@PathVariable("id") Long id) throws RecordNotFoundException {

        var modelTypeToDelete = service.findById(id);

        if(Objects.isNull(modelTypeToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The model type with id %d is not found", id)
            );
        }

        var deletedModelType = service.delete(modelTypeToDelete);
        var modelTypeResponse = mapper.performMapping(deletedModelType, ModelTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A model type has been deleted",
                modelTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

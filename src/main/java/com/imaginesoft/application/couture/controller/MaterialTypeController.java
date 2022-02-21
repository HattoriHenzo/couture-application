package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.dto.MaterialTypeDto;
import com.imaginesoft.application.couture.model.MaterialType;
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
public class MaterialTypeController extends GenericController<Success, MaterialTypeDto, MaterialType> {

    @Autowired
    protected MaterialTypeController(GenericService<MaterialType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/material-types/{id}")
    protected ResponseEntity<Success> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        var materialType = service.findById(id);
        var materialTypeResponse = mapper.performMapping(materialType, MaterialTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the material type with id %d", id),
                materialTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @GetMapping("/material-types")
    protected ResponseEntity<Success> findAll() throws RecordNotFoundException {
        var materialTypes = service.findAll();
        var materialTypesDtoResponse = materialTypes.stream()
                .map(client -> mapper.performMapping(client, MaterialTypeDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "Get the list of all the material types",
                materialTypesDtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PostMapping("/material-types")
    protected ResponseEntity<Success> create(@RequestBody MaterialTypeDto materialTypeDto) {
        var materialTypeToCreate = mapper.performMapping(materialTypeDto, MaterialType.class);
        var createdMaterialType = service.createOrUpdate(materialTypeToCreate);
        var materialTypeResponse = mapper.performMapping(createdMaterialType, ClientDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A new material type has been created",
                materialTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PutMapping("/material-types")
    protected ResponseEntity<Success> update(@RequestBody MaterialTypeDto materialTypeDto) {
        var materialTypeToUpdate = mapper.performMapping(materialTypeDto, MaterialType.class);
        var updatedMaterialType = service.createOrUpdate(materialTypeToUpdate);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A material type has been updated",
                updatedMaterialType);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @DeleteMapping("/material-types/{id}")
    protected ResponseEntity<Success> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        var materialTypeToDelete = service.findById(id);
        if(Objects.isNull(materialTypeToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The material type with id %d is not found", id)
            );
        }

        var deletedMaterialType = service.delete(materialTypeToDelete);
        var materialTypeResponse = mapper.performMapping(deletedMaterialType, ClientDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A material type has been deleted",
                materialTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

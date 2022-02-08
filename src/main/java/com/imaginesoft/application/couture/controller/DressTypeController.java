package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.model.DressType;
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
public class DressTypeController extends GenericController<Success, DressTypeDto, DressType> {

    @Autowired
    public DressTypeController(GenericService<DressType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/dress-types/{id}")
    protected ResponseEntity<Success> findById(@PathVariable("id") Long id) throws RecordNotFoundException {

        var dressType = service.findById(id);
        var dressTypeResponse = mapper.performMapping(dressType, DressTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the dress type with id %d", id),
                dressTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @GetMapping("/dress-types")
    protected ResponseEntity<Success> findAll() throws RecordNotFoundException {

        var dressTypes = service.findAll();
        var dressTypesDtoResponse = dressTypes.stream()
                .map(dressType -> mapper.performMapping(dressType, DressTypeDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "Get the list of all the dress type",
                dressTypesDtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PostMapping("/dress-types")
    protected ResponseEntity<Success> create(@RequestBody DressTypeDto dressTypeDto) {

        var dressTypeToCreate = mapper.performMapping(dressTypeDto, DressType.class);
        var createdDressType = service.createOrUpdate(dressTypeToCreate);
        var dressTypeResponse = mapper.performMapping(createdDressType, DressTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A new dress type has been created",
                dressTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PutMapping("/dress-types")
    protected ResponseEntity<Success> update(@RequestBody DressTypeDto dressTypeDto) {

        var dressTypeToUpdate = mapper.performMapping(dressTypeDto, DressType.class);
        var updatedDressType = service.createOrUpdate(dressTypeToUpdate);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A dress type has been updated",
                updatedDressType);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @DeleteMapping("/dress-types/{id}")
    protected ResponseEntity<Success> delete(@PathVariable("id") Long id) throws RecordNotFoundException {

        var dressTypeToDelete = service.findById(id);

        if(Objects.isNull(dressTypeToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The dress type with id %d is not found", id)
            );
        }

        var deletedDressType = service.delete(dressTypeToDelete);
        var dressTypeResponse = mapper.performMapping(deletedDressType, ClientDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A dress type has been deleted",
                dressTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

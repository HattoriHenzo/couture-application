package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.model.MeasureType;
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
public class MeasureTypeController extends GenericController<Success, MeasureTypeDto, MeasureType> {

    @Autowired
    protected MeasureTypeController(GenericService<MeasureType> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/measure-types/{id}")
    protected ResponseEntity<Success> findById(@PathVariable("id") Long id) throws RecordNotFoundException {

        var measureType = service.findById(id);
        var measureTypeResponse = mapper.performMapping(measureType, MeasureTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the measure type with id %d", id),
                measureTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @GetMapping("/measure-types")
    protected ResponseEntity<Success> findAll() throws RecordNotFoundException {

        var measureTypes = service.findAll();
        var measureTypesDtoResponse = measureTypes.stream()
                .map(measureType -> mapper.performMapping(measureType, MeasureTypeDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "Get the list of all the measure type",
                measureTypesDtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PostMapping("/measure-types")
    protected ResponseEntity<Success> create(@RequestBody MeasureTypeDto measureTypeDto) {

        var measureTypeToCreate = mapper.performMapping(measureTypeDto, MeasureType.class);
        var createdMeasureType = service.createOrUpdate(measureTypeToCreate);
        var measureTypeResponse = mapper.performMapping(createdMeasureType, MeasureTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A new measure type has been created",
                measureTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PutMapping("/measure-types")
    protected ResponseEntity<Success> update(@RequestBody MeasureTypeDto measureTypeDto) {

        var measureTypeToUpdate = mapper.performMapping(measureTypeDto, MeasureType.class);
        var updatedMeasureType = service.createOrUpdate(measureTypeToUpdate);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A measure type has been updated",
                updatedMeasureType);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @DeleteMapping("/measure-types/{id}")
    protected ResponseEntity<Success> delete(@PathVariable("id") Long id) throws RecordNotFoundException {

        var measureTypeToDelete = service.findById(id);

        if(Objects.isNull(measureTypeToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The measure type with id %d is not found", id)
            );
        }

        var deletedMeasureType = service.delete(measureTypeToDelete);
        var measureTypeResponse = mapper.performMapping(deletedMeasureType, MeasureTypeDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A measure type has been deleted",
                measureTypeResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

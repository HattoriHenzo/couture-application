package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.dto.DressDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.model.Dress;
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
public class DressController extends GenericController<DressDto, Dress> {

    @Autowired
    public DressController(GenericService<Dress> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/dresses/{id}")
    protected ResponseEntity<Success> findById(Long id) throws RecordNotFoundException {
        var dress = service.findById(id);
        var dressResponse = mapper.performMapping(dress, DressDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                String.format("Get the dress with id %d", id),
                dressResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @GetMapping("/dresses")
    protected ResponseEntity<Success> findAll() throws RecordNotFoundException {
        var dresses = service.findAll();
        var dressesDtoResponse = dresses.stream()
                .map(dress -> mapper.performMapping(dress, DressDto.class))
                .collect(Collectors.toList());
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "Get the list of all the dresses",
                dressesDtoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PostMapping("/dresses")
    protected ResponseEntity<Success> create(DressDto dressDto) {
        var dressToCreate = mapper.performMapping(dressDto, Dress.class);
        var createdDress = service.createOrUpdate(dressToCreate);
        var dressResponse = mapper.performMapping(createdDress, DressDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A new dress has been created",
                dressResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @PutMapping("/dresses")
    protected ResponseEntity<Success> update(DressDto dressDto) {
        var dressToUpdate = mapper.performMapping(dressDto, Dress.class);
        var updatedDress = service.createOrUpdate(dressToUpdate);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A dress has been updated",
                updatedDress);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    @DeleteMapping("/dresses/{id}")
    protected ResponseEntity<Success> delete(Long id) throws RecordNotFoundException {
        var dressToDelete = service.findById(id);
        if(Objects.isNull(dressToDelete)) {
            throw new RecordNotFoundException(
                    String.format("The dress with id %d is not found", id)
            );
        }

        var deletedDress = service.delete(dressToDelete);
        var dressResponse = mapper.performMapping(deletedDress, DressDto.class);
        var success = new Success(HttpStatus.OK,
                dateTime.getCurrentDateTime(Clock.systemDefaultZone()),
                "A client has been deleted",
                dressResponse);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}

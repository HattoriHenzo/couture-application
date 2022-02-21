package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.EmployeeDto;
import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1)
public class EmployeeController extends GenericController<Success, EmployeeDto, Employee> {

    @Autowired
    protected EmployeeController(GenericService<Employee> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime);
    }

    @Override
    @GetMapping("/employees/{id}")
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
    protected ResponseEntity<Success> findAll() throws RecordNotFoundException {
        return null;
    }

    @Override
    protected ResponseEntity<Success> create(EmployeeDto object) {
        return null;
    }

    @Override
    protected ResponseEntity<Success> update(EmployeeDto object) {
        return null;
    }

    @Override
    protected ResponseEntity<Success> delete(Long id) throws RecordNotFoundException {
        return null;
    }
}

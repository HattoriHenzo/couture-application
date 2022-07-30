package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.EmployeeDto;
import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.generic.service.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_ADMIN)
public class EmployeeController extends GenericController<EmployeeDto, Employee> {

    @Autowired
    protected EmployeeController(GenericService<Employee> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, EmployeeDto.class, Employee.class);
    }

    @Override
    @GetMapping("/employees/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/employees")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/employees")
    protected ResponseEntity<Response> create(@RequestBody EmployeeDto employee) throws BadRequestException {
        return super.create(employee);
    }

    @Override
    @PutMapping("/employees")
    protected ResponseEntity<Response> update(@RequestBody EmployeeDto employee) throws BadRequestException {
        return super.update(employee);
    }

    @Override
    @DeleteMapping("/employees/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}

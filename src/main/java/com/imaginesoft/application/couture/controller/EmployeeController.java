package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.EmployeeDto;
import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1)
public class EmployeeController extends GenericController<EmployeeDto, Employee> {

    @Autowired
    protected EmployeeController(GenericService<Employee> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, EmployeeDto.class, Employee.class);
    }

    @Override
    @GetMapping("/employees/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/employees")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/employees")
    protected ResponseEntity<Response> create(@RequestBody EmployeeDto employee) {
        return super.create(employee);
    }

    @Override
    @PutMapping("/employees")
    protected ResponseEntity<Response> update(@RequestBody EmployeeDto employee) {
        return super.update(employee);
    }

    @Override
    @DeleteMapping("/employees/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}

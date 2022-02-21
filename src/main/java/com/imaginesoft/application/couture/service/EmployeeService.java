package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.repository.EmployeeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EmployeeService extends GenericService<Employee> {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(Long id) throws RecordNotFoundException {
        var dressType = repository.findById(id);
        return dressType.orElseThrow(
                () -> new RecordNotFoundException("No record found"));
    }

    @Override
    public List<Employee> findAll() throws RecordNotFoundException {
        var employee = repository.findAll();
        if(employee.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return employee;
    }

    @Override
    public Employee createOrUpdate(Employee employee) {
        validateDomainRecord(employee);
        return repository.save(employee);
    }

    @Override
    public Employee delete(Employee employee) {
        var employeeToDelete = repository.findById(employee.getId());
        AtomicReference<Employee> deletedEmployee = new AtomicReference<>();

        employeeToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedEmployee.set(value);
        });

        return deletedEmployee.get();
    }
}

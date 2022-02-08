package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.repository.EmployeeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends GenericService<Employee> {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee createOrUpdate(Employee object) {
        return null;
    }

    @Override
    public Employee delete(Employee employee) {
        return null;
    }
}

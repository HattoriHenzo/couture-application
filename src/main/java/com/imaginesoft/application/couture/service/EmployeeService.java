package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.repository.EmployeeRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends GenericService<Employee> {

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }
}

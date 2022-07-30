package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.repository.EmployeeRepository;
import com.imaginesoft.application.couture.service.exception.DomainRecordNotFoundException;
import com.imaginesoft.application.couture.service.exception.DomainRulesException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest implements WithAssertions {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenEmployee_whenFindingEmployee_thenFindAllEmployees() throws DomainRecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewEmployees());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenEmployee_whenCreateEmployee_thenEmployeeIsCreated() {
        var newEmployee = createNewEmployee();
        when(repository.save(newEmployee)).thenReturn(newEmployee);
        var createdEmployee = underTest.createOrUpdate(newEmployee);

        assertThat(createdEmployee).isNotNull();
    }

    @Test
    void givenEmployee_whenFirstNameIsEmpty_thenEmployeeThrowException() {
        var newEmployee = createNewEmployee();
        newEmployee.setFirstName(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newEmployee));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenEmployee_whenLastNameIsEmpty_thenEmployeeThrowException() {
        var newEmployee = createNewEmployee();
        newEmployee.setLastName(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newEmployee));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenEmployee_whenTelephoneIsEmpty_thenEmployeeThrowException() {
        var newEmployee = createNewEmployee();
        newEmployee.setTelephone(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newEmployee));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenEmployee_whenUpdateEmployee_thenEmployeeIsUpdated() {
        var employee = createNewEmployee();
        employee.setFirstName("POLO");
        when(repository.save(employee)).thenReturn(employee);
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(employee)).isNotNull(),
                () -> {
                    var updatedClient = underTest.findById(EMPLOYEE_ID);
                    assertAll(
                            () -> assertThat(updatedClient.getFirstName()).isEqualTo(employee.getFirstName()),
                            () -> assertThat(updatedClient.getLastName()).isEqualTo(employee.getLastName()),
                            () -> assertThat(updatedClient.getTelephone()).isEqualTo(employee.getTelephone()),
                            () -> assertThat(updatedClient.getGender()).isEqualTo(employee.getGender())
                    );
                }
        );
    }

    @Test
    void givenEmployee_whenDeleteEmployee_thenEmployeeIsDeleted() {
        var deletedEmployee = createNewEmployee();
        when(repository.findById(anyLong())).thenReturn(Optional.of(deletedEmployee));

        assertThat(underTest.delete(deletedEmployee.getId())).isNotNull();
    }
}

package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DataJpaTest
@ActiveProfiles("test")
class EmployeeRepositoryTest implements WithAssertions {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void givenEmployees_whenGettingEmployees_thenGetAllEmployees() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenDressType_whenGettingDressTypeById_thenGetDressType() {
        var foundEmployee = repository.findById(EMPLOYEE_ID);
        assumingThat(foundEmployee.isPresent(), () -> foundEmployee.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(EMPLOYEE_ID),
                        () -> assertThat(value.getFirstName()).isEqualTo(EMPLOYEE_FIRST_NAME),
                        () -> assertThat(value.getLastName()).isEqualTo(EMPLOYEE_LAST_NAME),
                        () -> assertThat(value.getTelephone()).isEqualTo(EMPLOYEE_TELEPHONE),
                        () -> assertThat(value.getGender()).isEqualTo(EMPLOYEE_GENDER_MALE)
                )
        ));
    }

    @Test
    void givenEmployee_whenCreateEmployee_thenEmployeeExists() {
        var newEmployee = createNewEmployee();
        var createdEmployee = repository.save(newEmployee);

        assertAll(
                () -> assertThat(createdEmployee).isNotNull(),
                () -> assertThat(createdEmployee.getFirstName()).isEqualTo(EMPLOYEE_FIRST_NAME),
                () -> assertThat(createdEmployee.getLastName()).isEqualTo(EMPLOYEE_LAST_NAME),
                () -> assertThat(createdEmployee.getTelephone()).isEqualTo(EMPLOYEE_TELEPHONE)
        );
    }

    @Test
    void givenClient_whenUpdateEmployee_thenEmployeeHasChanged() {
        var employeeToUpdate = repository.findById(EMPLOYEE_ID);
        assumingThat(employeeToUpdate.isPresent(), () -> employeeToUpdate.ifPresent(
                value -> {
                    value.setFirstName(EMPLOYEE_EDITED_FIRST_NAME);
                    value.setTelephone(EMPLOYEE_EDITED_TELEPHONE);
                    var updatedEmployee = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedEmployee.getFirstName()).isEqualTo(employeeToUpdate.get().getFirstName()),
                            () -> assertThat(updatedEmployee.getTelephone()).isEqualTo(employeeToUpdate.get().getTelephone())
                    );
                }));
    }

    @Test
    void givenClient_whenDeleteEmployee_thenEmployeeDoesNotExists() {
        var employeeToDelete = repository.findById(EMPLOYEE_ID);
        assumingThat(employeeToDelete.isPresent(), () -> employeeToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedEmployee = repository.findById(EMPLOYEE_ID);
                    assertThat(deletedEmployee).isNotPresent();
                }
        ));
    }
}
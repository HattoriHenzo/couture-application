package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class EmployeeRepositoryTest implements WithAssertions {

    @Autowired
    private EmployeeRepository repository;

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
        var newEmployee = createNewEmployee();
        var employeeToUpdate = repository.save(newEmployee);
        employeeToUpdate.setFirstName(EMPLOYEE_EDITED_FIRST_NAME);
        employeeToUpdate.setTelephone(EMPLOYEE_EDITED_TELEPHONE);
        var updatedEmployee = repository.save(employeeToUpdate);

        assertAll(
                () -> assertThat(updatedEmployee.getFirstName()).isEqualTo(EMPLOYEE_EDITED_FIRST_NAME),
                () -> assertThat(updatedEmployee.getTelephone()).isEqualTo(EMPLOYEE_EDITED_TELEPHONE)
        );
    }

    @Test
    void givenClient_whenDeleteEmployee_thenEmployeeDoesNotExists() {
        var employeeToDelete = repository.findById(EMPLOYEE_ID);
        repository.delete(employeeToDelete.get());
        var deletedEmployee = repository.findById(EMPLOYEE_ID);

        assertThat(deletedEmployee).isNotPresent();
    }
}
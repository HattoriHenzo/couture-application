package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.model.Gender;
import com.imaginesoft.application.couture.model.generic.GenericPerson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-h2-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

    private final Long ID_3 = 3L;
    private final String FIRST_NAME = "Samuel";
    private final String EDITED_FIRST_NAME = "Thierry";
    private final String LAST_NAME = "Jeans";
    private final String TELEPHONE = "418-555-9999";
    private final String EDITED_TELEPHONE = "418-333-9988";
    private final Gender GENDER_MALE = Gender.MALE;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository repository;

    @Test
    @Order(1)
    void givenEmployee_whenCreateEmployee_thenEmployeeExists() {

        GenericPerson newEmployee = createNewEmployee();
        GenericPerson createdEmployee = entityManager.persist(newEmployee);

        Assertions.assertAll(
                () -> assertThat(createdEmployee).isNotNull(),
                () -> assertThat(createdEmployee.getFirstName()).isEqualTo(FIRST_NAME),
                () -> assertThat(createdEmployee.getLastName()).isEqualTo(LAST_NAME),
                () -> assertThat(createdEmployee.getTelephone()).isEqualTo(TELEPHONE)
        );
    }

    @Test
    @Order(2)
    void givenClient_whenUpdateEmployee_thenEmployeeHasChanged() {

        Employee newEmployee = createNewEmployee();

        Employee employeeToUpdate = entityManager.persist(newEmployee);
        employeeToUpdate.setFirstName(EDITED_FIRST_NAME);
        employeeToUpdate.setTelephone(EDITED_TELEPHONE);

        Employee updatedEmployee = repository.save(employeeToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedEmployee.getFirstName()).isEqualTo(EDITED_FIRST_NAME),
                () -> assertThat(updatedEmployee.getTelephone()).isEqualTo(EDITED_TELEPHONE)
        );
    }

    @Test
    @Order(3)
    void givenClient_whenDeleteEmployee_thenEmployeeDoesNotExists() {

        Employee newEmployee = createNewEmployee();

        Employee employeeToDelete = entityManager.persist(newEmployee);
        repository.delete(employeeToDelete);

        Optional<Employee> deletedEmployee = repository.findById(ID_3);

        assertThat(deletedEmployee).isNotPresent();
    }

    private Employee createNewEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(FIRST_NAME);
        newEmployee.setLastName(LAST_NAME);
        newEmployee.setTelephone(TELEPHONE);
        newEmployee.setGender(GENDER_MALE);
        return newEmployee;
    }
}
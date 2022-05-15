package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.EmployeeDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

 class EmployeeControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    assertThat(Objects.requireNonNull(result.getResponseBody()).getData()).isNotEmpty();
                });
    }

    @Test
    void integrationTest_For_FindById() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/employees/{ID}", EMPLOYEE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var employees = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<EmployeeDto>>() {
                            });
                    var foundEmployee = employees.get(0);
                    assertAll(
                            () -> assertThat(foundEmployee.getId()).isEqualTo(EMPLOYEE_ID),
                            () -> assertThat(foundEmployee.getFirstName()).isEqualTo(EMPLOYEE_FIRST_NAME),
                            () -> assertThat(foundEmployee.getLastName()).isEqualTo(EMPLOYEE_LAST_NAME),
                            () -> assertThat(foundEmployee.getGender()).isEqualTo(EMPLOYEE_GENDER_MALE.name()),
                            () -> assertThat(foundEmployee.getTelephone()).isEqualTo(EMPLOYEE_TELEPHONE)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newEmployee = createNewEmployeeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/employees")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var employees = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<EmployeeDto>>() {
                            });
                    var createdEmployee = employees.get(0);
                    assertAll(
                            () -> assertThat(createdEmployee.getId()).isEqualTo(newEmployee.getId()),
                            () -> assertThat(createdEmployee.getFirstName()).isEqualTo(newEmployee.getFirstName()),
                            () -> assertThat(createdEmployee.getLastName()).isEqualTo(newEmployee.getLastName()),
                            () -> assertThat(createdEmployee.getGender()).isEqualTo(newEmployee.getGender()),
                            () -> assertThat(createdEmployee.getTelephone()).isEqualTo(newEmployee.getTelephone())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var employeeToUpdate = createNewEmployeeDto();
        employeeToUpdate.setFirstName(EMPLOYEE_EDITED_FIRST_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/employees")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(employeeToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var employees = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<EmployeeDto>>() {
                            });
                    var updatedEmployee = employees.get(0);
                    assertAll(
                            () -> assertThat(updatedEmployee.getId()).isEqualTo(employeeToUpdate.getId()),
                            () -> assertThat(updatedEmployee.getFirstName()).isEqualTo(employeeToUpdate.getFirstName()),
                            () -> assertThat(updatedEmployee.getLastName()).isEqualTo(employeeToUpdate.getLastName()),
                            () -> assertThat(updatedEmployee.getGender()).isEqualTo(employeeToUpdate.getGender()),
                            () -> assertThat(updatedEmployee.getTelephone()).isEqualTo(employeeToUpdate.getTelephone())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/employees/{ID}", EMPLOYEE_TO_DELETE)
                .exchange()
                .expectStatus().isOk();
    }
}

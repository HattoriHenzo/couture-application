package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Gender;
import com.imaginesoft.application.couture.util.TestDataFactory;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;


class ClientControllerITest extends BaseIntegrationTest implements WithAssertions {

    private static Long ID = 2L;
    private static String FIRSTNAME = "STEPHEN";
    private static String LASTNAME = "CURRY";
    private static String TELEPHONE = "418-000-9090";
    private static Gender GENDER = Gender.MALE;
    private static String UPDATED_FIRSTNAME = "UPDATED_FIRSTNAME";

    @Autowired
    private ObjectMapper mapper;

    @Test
    void integrationTest_For_GetAll() {

        webTestClient.get()
                .uri("/api/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    assertThat(result.getResponseBody().getData()).isNotEmpty();
                });
    }

    @Test
    void integrationTest_For_GetById() {

        webTestClient.get()
                .uri("/api/clients/{ID}", ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var clients = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ClientDto>>() {
                            });
                    var client = clients.get(0);
                    assertAll(
                            () -> assertThat(client.getId()).isEqualTo(ID),
                            () -> assertThat(client.getFirstName()).isEqualTo(FIRSTNAME),
                            () -> assertThat(client.getLastName()).isEqualTo(LASTNAME),
                            () -> assertThat(client.getTelephone()).isEqualTo(TELEPHONE),
                            () -> assertThat(client.getGender()).isEqualTo(GENDER.name())
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {

        var newClient = TestDataFactory.createNewClientDto();

        webTestClient.post()
                .uri("/api/client")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newClient)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var clients = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ClientDto>>() {
                            });
                    var createdClient = clients.get(0);
                    assertAll(
                            () -> assertThat(createdClient.getId()).isEqualTo(newClient.getId()),
                            () -> assertThat(createdClient.getFirstName()).isEqualTo(newClient.getFirstName()),
                            () -> assertThat(createdClient.getLastName()).isEqualTo(newClient.getLastName()),
                            () -> assertThat(createdClient.getTelephone()).isEqualTo(newClient.getTelephone()),
                            () -> assertThat(createdClient.getGender()).isEqualTo(newClient.getGender())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {

        var clientToUpdate = TestDataFactory.createNewClientDto();
        clientToUpdate.setFirstName(UPDATED_FIRSTNAME);

        webTestClient.put()
                .uri("/api/client")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(clientToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var clients = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ClientDto>>() {
                            });
                    var updatedClient = clients.get(0);
                    assertAll(
                            () -> assertThat(updatedClient.getId()).isEqualTo(clientToUpdate.getId()),
                            () -> assertThat(updatedClient.getFirstName()).isEqualTo(clientToUpdate.getFirstName()),
                            () -> assertThat(updatedClient.getLastName()).isEqualTo(clientToUpdate.getLastName()),
                            () -> assertThat(updatedClient.getTelephone()).isEqualTo(clientToUpdate.getTelephone()),
                            () -> assertThat(updatedClient.getGender()).isEqualTo(clientToUpdate.getGender())
                    );

                });

    }

    @Test
    void integrationTest_For_Delete() {

        webTestClient.delete()
                .uri("/api/clients/{ID}", ID)
                .exchange()
                .expectStatus().isOk();
    }
}

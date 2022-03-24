package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ClientControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/clients")
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
                .uri(ApplicationDataFactory.API_V1 + "/clients/{ID}", CLIENT_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var clients = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ClientDto>>() {
                            });
                    var foundClient = clients.get(0);
                    assertAll(
                            () -> assertThat(foundClient.getId()).isEqualTo(CLIENT_ID),
                            () -> assertThat(foundClient.getFirstName()).isEqualTo(CLIENT_FIRST_NAME),
                            () -> assertThat(foundClient.getLastName()).isEqualTo(CLIENT_LAST_NAME),
                            () -> assertThat(foundClient.getTelephone()).isEqualTo(CLIENT_TELEPHONE),
                            () -> assertThat(foundClient.getGender()).isEqualTo(CLIENT_GENDER_MALE.name())
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newClient = createNewClientDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/clients")
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
        var clientToUpdate = createNewClientDto();
        clientToUpdate.setFirstName(CLIENT_EDITED_FIRST_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/clients")
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
                .uri(ApplicationDataFactory.API_V1 + "/clients/{ID}", CLIENT_ID)
                .exchange()
                 .expectStatus().isOk();
    }
}

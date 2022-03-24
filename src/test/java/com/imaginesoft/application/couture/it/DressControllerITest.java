package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class DressControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/dresses")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> assertThat(Objects.requireNonNull(result.getResponseBody()).getData()).isNotEmpty());
    }

    @Test
    void integrationTest_For_FindById() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/dresses/{ID}", DRESS_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dresses = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressDto>>() {
                            });
                    var foundDress = dresses.get(0);
                    assertAll(
                            () -> assertThat(foundDress.getId()).isEqualTo(DRESS_ID),
                            () -> assertThat(foundDress.getAmount()).isEqualTo(DRESS_AMOUNT),
                            () -> assertThat(foundDress.getDressType().getName()).isEqualTo(DRESS_TYPE_NAME),
                            () -> assertThat(foundDress.getMaterialType().getName()).isEqualTo(MATERIAL_TYPE_NAME),
                            () -> assertThat(foundDress.getModelType().getName()).isEqualTo(MODEL_TYPE_NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newDress = createNewDressDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/dresses")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newDress)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dresses = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressDto>>() {
                            });
                    var createdDress = dresses.get(0);
                    assertAll(
                            () -> assertThat(createdDress.getId()).isEqualTo(newDress.getId()),
                            () -> assertThat(createdDress.getAmount()).isEqualTo(newDress.getAmount()),
                            () -> assertThat(createdDress.getDressType().getName()).isEqualTo(newDress.getDressType().getName()),
                            () -> assertThat(createdDress.getMaterialType().getName()).isEqualTo(newDress.getMaterialType().getName()),
                            () -> assertThat(createdDress.getModelType().getName()).isEqualTo(newDress.getModelType().getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var dressToUpdate = createNewDressDto();
        dressToUpdate.setAmount(DRESS_EDITED_AMOUNT);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/dresses")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dressToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dresses = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressDto>>() {
                            });
                    var updatedDress = dresses.get(0);
                    assertAll(
                            () -> assertThat(updatedDress.getId()).isEqualTo(dressToUpdate.getId()),
                            () -> assertThat(updatedDress.getAmount()).isEqualTo(dressToUpdate.getAmount())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/dresses/{ID}", ID)
                .exchange()
                .expectStatus().isOk();
    }
}

package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class DressTypeControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
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
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/{ID}", DRESS_TYPE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var foundDressType = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(foundDressType.getId()).isEqualTo(DRESS_TYPE_ID),
                            () -> assertThat(foundDressType.getName()).isEqualTo(DRESS_TYPE_NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newDressTypeDto = createNewDressTypeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newDressTypeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var createdDressType = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(createdDressType.getId()).isEqualTo(newDressTypeDto.getId()),
                            () -> assertThat(createdDressType.getName()).isEqualTo(newDressTypeDto.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var dressTypeToUpdate = createNewDressTypeDto();
        dressTypeToUpdate.setName(DRESS_TYPE_EDITED_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dressTypeToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var updatedDressType = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(updatedDressType.getId()).isEqualTo(dressTypeToUpdate.getId()),
                            () -> assertThat(updatedDressType.getName()).isEqualTo(dressTypeToUpdate.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/{ID}", DRESS_TYPE_TO_DELETE)
                .exchange()
                .expectStatus().isOk();
    }
}

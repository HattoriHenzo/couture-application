package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ModelTypeControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
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
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/{ID}", MODEL_TYPE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var modelTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ModelTypeDto>>() {
                            });
                    var foundModelType = modelTypes.get(0);
                    assertAll(
                            () -> assertThat(foundModelType.getId()).isEqualTo(MODEL_TYPE_ID),
                            () -> assertThat(foundModelType.getName()).isEqualTo(MODEL_TYPE_NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newModelType = createNewDressTypeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newModelType)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var modelTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ModelTypeDto>>() {
                            });
                    var createdModelType = modelTypes.get(0);
                    assertAll(
                            () -> assertThat(createdModelType.getId()).isEqualTo(newModelType.getId()),
                            () -> assertThat(createdModelType.getName()).isEqualTo(newModelType.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var modelTypeToUpdate = createNewDressTypeDto();
        modelTypeToUpdate.setName(MODEL_TYPE_EDITED_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(modelTypeToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var modelTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var updatedModelType = modelTypes.get(0);
                    assertAll(
                            () -> assertThat(updatedModelType.getId()).isEqualTo(modelTypeToUpdate.getId()),
                            () -> assertThat(updatedModelType.getName()).isEqualTo(modelTypeToUpdate.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/{ID}", MODEL_TYPE_TO_DELETE)
                .exchange()
                .expectStatus().isOk();
    }
}

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

import static com.imaginesoft.application.couture.TestDataFactory.createNewDressTypeDto;
import static org.junit.jupiter.api.Assertions.assertAll;

class ModelTypeControllerITest extends BaseIntegrationTest {

    private static Long ID = 1L;
    private static String NAME = "SHERPA";
    private static String UPDATED_NAME = "UPDATED_NAME";

    @Test
    void integrationTest_For_FindAll() {

        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/model-types")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    assertThat(result.getResponseBody().getData()).isNotEmpty();
                });
    }

    @Test
    void integrationTest_For_FindById() {

        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/model-types/{ID}", ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var modelTypesDto = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ModelTypeDto>>() {
                            });
                    var modelTypeDto = modelTypesDto.get(0);
                    assertAll(
                            () -> assertThat(modelTypeDto.getId()).isEqualTo(ID),
                            () -> assertThat(modelTypeDto.getName()).isEqualTo(NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {

        var newModelTypeDto = createNewDressTypeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/model-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newModelTypeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var modelTypesDto = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<ModelTypeDto>>() {
                            });
                    var createdModelTypeDto = modelTypesDto.get(0);
                    assertAll(
                            () -> assertThat(createdModelTypeDto.getId()).isEqualTo(newModelTypeDto.getId()),
                            () -> assertThat(createdModelTypeDto.getName()).isEqualTo(newModelTypeDto.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {

        var modelTypeToUpdateDto = createNewDressTypeDto();
        modelTypeToUpdateDto.setName(UPDATED_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/model-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(modelTypeToUpdateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var modelTypesDto = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var updatedModelTypeDto = modelTypesDto.get(0);
                    assertAll(
                            () -> assertThat(updatedModelTypeDto.getId()).isEqualTo(modelTypeToUpdateDto.getId()),
                            () -> assertThat(updatedModelTypeDto.getName()).isEqualTo(modelTypeToUpdateDto.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {

        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/model-types/{ID}", ID)
                .exchange()
                .expectStatus().isOk();
    }
}

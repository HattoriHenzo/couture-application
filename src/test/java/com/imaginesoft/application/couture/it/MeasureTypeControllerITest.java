package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class MeasureTypeControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/measure-types")
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
                .uri(ApplicationDataFactory.API_V1 + "/measure-types/{ID}", MEASURE_TYPE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var measureTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MeasureTypeDto>>() {
                            });
                    var foundMeasureType = measureTypes.get(0);
                    assertAll(
                            () -> assertThat(foundMeasureType.getId()).isEqualTo(MEASURE_TYPE_ID),
                            () -> assertThat(foundMeasureType.getName()).isEqualTo(MEASURE_TYPE_NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newMeasureType = createNewMeasureTypeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/measure-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newMeasureType)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var newMeasureTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MeasureTypeDto>>() {
                            });
                    var createdMeasureType = newMeasureTypes.get(0);
                    assertAll(
                            () -> assertThat(createdMeasureType.getId()).isEqualTo(newMeasureType.getId()),
                            () -> assertThat(createdMeasureType.getName()).isEqualTo(newMeasureType.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var measureTypeToUpdate = createNewMeasureTypeDto();
        measureTypeToUpdate.setName(MEASURE_TYPE_EDITED_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/measure-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(measureTypeToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var measureTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MeasureTypeDto>>() {
                            });
                    var updatedMeasureType = measureTypes.get(0);
                    assertAll(
                            () -> assertThat(updatedMeasureType.getId()).isEqualTo(measureTypeToUpdate.getId()),
                            () -> assertThat(updatedMeasureType.getName()).isEqualTo(measureTypeToUpdate.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/measure-types/{ID}", MEASURE_TYPE_ID)
                .exchange()
                .expectStatus().isOk();
    }
}

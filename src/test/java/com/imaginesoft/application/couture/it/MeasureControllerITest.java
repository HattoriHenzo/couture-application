package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.MeasureDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class MeasureControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/measures")
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
                .uri(ApplicationDataFactory.API_V1 + "/measures/{ID}", MEASURE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var measures = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MeasureDto>>() {
                            });
                    var foundMeasure = measures.get(0);
                    assertAll(
                            () -> assertThat(foundMeasure.getId()).isEqualTo(MEASURE_ID),
                            () -> assertThat(foundMeasure.getValue()).isEqualTo(MEASURE_VALUE),
                            () -> assertThat(foundMeasure.getMeasureType().getName()).isEqualTo(MEASURE_TYPE_NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newMeasure = createNewMeasureDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/measures")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newMeasure)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var measures = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MeasureDto>>() {
                            });
                    var createdMeasure = measures.get(0);
                    assertAll(
                            () -> assertThat(createdMeasure.getId()).isEqualTo(newMeasure.getId()),
                            () -> assertThat(createdMeasure.getValue()).isEqualTo(newMeasure.getValue()),
                            () -> assertThat(createdMeasure.getMeasureType().getName()).isEqualTo(newMeasure.getMeasureType().getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var measureToUpdate = createNewMeasureDto();
        measureToUpdate.setValue(MEASURE_EDITED_VALUE);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/measures")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(measureToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var measures = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MeasureDto>>() {
                            });
                    var updatedMeasure = measures.get(0);
                    assertAll(
                            () -> assertThat(updatedMeasure.getId()).isEqualTo(measureToUpdate.getId()),
                            () -> assertThat(updatedMeasure.getValue()).isEqualTo(measureToUpdate.getValue())
                    );

                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/measures/{ID}", MEASURE_ID)
                .exchange()
                .expectStatus().isOk();
    }
}

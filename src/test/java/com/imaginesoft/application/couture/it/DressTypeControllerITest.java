package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.createNewDressTypeDto;
import static org.junit.jupiter.api.Assertions.assertAll;

class DressTypeControllerITest extends BaseIntegrationTest {

    private static Long ID = 1L;
    private static String NAME = "PANTS";
    private static String UPDATED_NAME = "UPDATED_NAME";

    @Test
    void integrationTest_For_FindAll() {

        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/dress-types")
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
                .uri(ApplicationDataFactory.API_V1 + "/dress-types/{ID}", ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var dressType = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(dressType.getId()).isEqualTo(ID),
                            () -> assertThat(dressType.getName()).isEqualTo(NAME)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {

        var newDressTypeDto = createNewDressTypeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/dress-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newDressTypeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var createdDressTypeDto = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(createdDressTypeDto.getId()).isEqualTo(newDressTypeDto.getId()),
                            () -> assertThat(createdDressTypeDto.getName()).isEqualTo(newDressTypeDto.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {

        var dressTypeToUpdateDto = createNewDressTypeDto();
        dressTypeToUpdateDto.setName(UPDATED_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/dress-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dressTypeToUpdateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var updatedDressTypeDto = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(updatedDressTypeDto.getId()).isEqualTo(dressTypeToUpdateDto.getId()),
                            () -> assertThat(updatedDressTypeDto.getName()).isEqualTo(dressTypeToUpdateDto.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {

        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/dress-types/{ID}", ID)
                .exchange()
                .expectStatus().isOk();
    }
}

package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.MaterialTypeDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class MaterialTypeControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/material-types")
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
                .uri(ApplicationDataFactory.API_V1 + "/material-types/{ID}", MATERIAL_TYPE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var materialTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MaterialTypeDto>>() {
                            });
                    var foundMaterialType = materialTypes.get(0);
                    assertAll(
                            () -> assertThat(foundMaterialType.getId()).isEqualTo(MATERIAL_TYPE_ID),
                            () -> assertThat(foundMaterialType.getName()).isEqualTo(MATERIAL_TYPE_NAME),
                            () -> assertThat(foundMaterialType.getImage()).isEqualTo(MATERIAL_TYPE_IMAGE)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newMaterialTypeDto = createNewMaterialTypeDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/material-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newMaterialTypeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var materialTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<MaterialTypeDto>>() {
                            });
                    var createdMaterialType = materialTypes.get(0);
                    assertAll(
                            () -> assertThat(createdMaterialType.getId()).isEqualTo(newMaterialTypeDto.getId()),
                            () -> assertThat(createdMaterialType.getName()).isEqualTo(newMaterialTypeDto.getName()),
                            () -> assertThat(createdMaterialType.getImage()).isEqualTo(newMaterialTypeDto.getImage())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var materialTypeToUpdate = createNewMaterialTypeDto();
        materialTypeToUpdate.setName(MATERIAL_TYPE_EDITED_NAME);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/material-types")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(materialTypeToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var dressTypes = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<DressTypeDto>>() {
                            });
                    var updatedDressType = dressTypes.get(0);
                    assertAll(
                            () -> assertThat(updatedDressType.getId()).isEqualTo(materialTypeToUpdate.getId()),
                            () -> assertThat(updatedDressType.getName()).isEqualTo(materialTypeToUpdate.getName())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/dress-types/{ID}", MATERIAL_TYPE_ID)
                .exchange()
                .expectStatus().isOk();
    }
}

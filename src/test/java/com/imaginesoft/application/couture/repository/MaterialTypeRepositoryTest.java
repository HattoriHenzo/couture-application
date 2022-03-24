package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DataJpaTest
@ActiveProfiles("test")
class MaterialTypeRepositoryTest implements WithAssertions {

    @Autowired
    private MaterialTypeRepository repository;

    @Test
    void givenMaterialTypes_whenGettingMaterialTypes_thenGetAllMaterialTypes() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenMaterialType_whenGettingMaterialTypeById_thenGetMaterialTypes() {
        var foundMaterialType = repository.findById(MATERIAL_TYPE_ID);
        assumingThat(foundMaterialType.isPresent(), () -> foundMaterialType.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(MATERIAL_TYPE_ID),
                        () -> assertThat(value.getName()).isEqualTo(MATERIAL_TYPE_NAME),
                        () -> assertThat(value.getImage()).isEqualTo(MATERIAL_TYPE_IMAGE)
                )
        ));
    }

    @Test
    void givenMaterialType_whenCreateMaterialType_thenMaterialTypeExists() {
        var newMaterialType = createNewMaterialType();
        var createdMaterialType = repository.save(newMaterialType);

        assertAll(
                () -> assertThat(createdMaterialType).isNotNull(),
                () -> assertThat(createdMaterialType.getName()).isEqualTo(MATERIAL_TYPE_NAME),
                () -> assertThat(createdMaterialType.getImage()).isEqualTo(MATERIAL_TYPE_IMAGE)
        );
    }

    @Test
    void givenMaterialType_whenUpdateMaterialType_thenMaterialTypeHasChanged() {
        var materialTypeToUpdate = repository.findById(MATERIAL_TYPE_ID);
        assumingThat(materialTypeToUpdate.isPresent(), () -> materialTypeToUpdate.ifPresent(
                value -> {
                    value.setName(MATERIAL_TYPE_EDITED_NAME);
                    value.setImage(MATERIAL_TYPE_EDITED_IMAGE);
                    var updatedMaterialType = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedMaterialType.getId()).isEqualTo(materialTypeToUpdate.get().getId()),
                            () -> assertThat(updatedMaterialType.getName()).isEqualTo(materialTypeToUpdate.get().getName()),
                            () -> assertThat(updatedMaterialType.getImage()).isEqualTo(materialTypeToUpdate.get().getImage())
                    );
                }
        ));
    }

    @Test
    void givenMaterialType_whenDeleteMaterialType_thenMaterialTypeDoesNotExists() {
        var materialTypeToDelete = repository.findById(MATERIAL_TYPE_ID);
        assumingThat(materialTypeToDelete.isPresent(), () -> materialTypeToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedMaterialType = repository.findById(MATERIAL_TYPE_ID);
                    assertThat(deletedMaterialType).isNotPresent();
                }
        ));
    }
}
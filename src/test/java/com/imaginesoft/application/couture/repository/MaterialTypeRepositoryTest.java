package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class MaterialTypeRepositoryTest implements WithAssertions {

    @Autowired
    private MaterialTypeRepository repository;

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

        var newMaterialType = createNewMaterialType();

        var materialTypeToUpdate = repository.save(newMaterialType);
        materialTypeToUpdate.setName(MATERIAL_TYPE_EDITED_NAME);
        materialTypeToUpdate.setImage(MATERIAL_TYPE_EDITED_IMAGE);

        var updatedMaterialType = repository.save(materialTypeToUpdate);

        assertAll(
                () -> assertThat(updatedMaterialType.getId()).isEqualTo(materialTypeToUpdate.getId()),
                () -> assertThat(updatedMaterialType.getName()).isEqualTo(MATERIAL_TYPE_EDITED_NAME),
                () -> assertThat(updatedMaterialType.getImage()).isEqualTo(MATERIAL_TYPE_EDITED_IMAGE)
        );
    }

    @Test
    void givenMaterialType_whenDeleteMaterialType_thenMaterialTypeDoesNotExists() {

        var materialTypeToDelete = repository.findById(MATERIAL_TYPE_ID);
        repository.delete(materialTypeToDelete.get());
        var deletedMaterialType = repository.findById(MATERIAL_TYPE_ID);

        assertThat(deletedMaterialType).isNotPresent();
    }
}
package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.MaterialType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-h2-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MaterialTypeRepositoryTest {

    private final Long ID_3 = 3L;
    private final String NAME = "COTTON";
    private final String EDITED_NAME = "NYLON";
    private final String IMAGE = "image_path";
    private final String EDITED_IMAGE = "image_path_edit";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MaterialTypeRepository repository;

    @Test
    @Order(1)
    void givenMaterialType_whenCreateMaterialType_thenMaterialTypeExists() {

        MaterialType newMaterialType = createNewMaterialType();
        MaterialType createdMaterialType = entityManager.persist(newMaterialType);

        Assertions.assertAll(
                () -> assertThat(createdMaterialType).isNotNull(),
                () -> assertThat(createdMaterialType.getName()).isEqualTo(NAME),
                () -> assertThat(createdMaterialType.getImage()).isEqualTo(IMAGE)
        );
    }

    @Test
    @Order(2)
    void givenMaterialType_whenUpdateMaterialType_thenMaterialTypeHasChanged() {

        MaterialType newMaterialType = createNewMaterialType();

        MaterialType materialTypeToUpdate = entityManager.persist(newMaterialType);
        materialTypeToUpdate.setName(EDITED_NAME);
        materialTypeToUpdate.setImage(EDITED_IMAGE);

        MaterialType updatedMaterialType = repository.save(materialTypeToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedMaterialType.getId()).isEqualTo(materialTypeToUpdate.getId()),
                () -> assertThat(updatedMaterialType.getName()).isEqualTo(EDITED_NAME),
                () -> assertThat(updatedMaterialType.getImage()).isEqualTo(EDITED_IMAGE)
        );
    }

    @Test
    @Order(3)
    void givenMaterialType_whenDeleteMaterialType_thenMaterialTypeDoesNotExists() {

        MaterialType newMaterialType = createNewMaterialType();

        MaterialType materialTypeToDelete = entityManager.persist(newMaterialType);
        repository.delete(materialTypeToDelete);

        Optional<MaterialType> deletedMaterialType = repository.findById(ID_3);

        assertThat(deletedMaterialType).isNotPresent();
    }

    private MaterialType createNewMaterialType() {
        MaterialType newMaterialType = new MaterialType();
        newMaterialType.setName(NAME);
        newMaterialType.setImage(IMAGE);
        return newMaterialType;
    }
}
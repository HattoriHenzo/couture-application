package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.ModelType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModelTypeRepositoryTest {

    private final Long ID_3 = 3L;
    private final String NAME = "Smoking";
    private final String EDITED_NAME = "African";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ModelTypeRepository repository;

    @Test
    @Order(1)
    void givenModelType_whenCreateModelType_thenModelTypeExists() {

        ModelType newModelType = createNewModelType();
        ModelType createdModelType = entityManager.persist(newModelType);

        Assertions.assertAll(
                () -> assertThat(createdModelType).isNotNull(),
                () -> assertThat(createdModelType.getName()).isEqualTo(NAME)
        );
    }

    @Test
    @Order(2)
    void givenModelType_whenUpdateModelType_thenModelTypeHasChanged() {

        ModelType newModelType = createNewModelType();

        ModelType modelTypeToUpdate = entityManager.persist(newModelType);
        modelTypeToUpdate.setName(EDITED_NAME);

        ModelType updatedModelType = repository.save(modelTypeToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedModelType.getId()).isEqualTo(modelTypeToUpdate.getId()),
                () -> assertThat(updatedModelType.getName()).isEqualTo(EDITED_NAME)
        );

    }

    @Test
    @Order(3)
    void givenModelType_whenDeleteModelType_thenModelTypeDoesNotExists() {

        ModelType newModelType = createNewModelType();

        ModelType modelTypeToDelete = entityManager.persist(newModelType);
        repository.delete(modelTypeToDelete);

        Optional<ModelType> deletedModelType = repository.findById(ID_3);

        assertThat(deletedModelType).isNotPresent();
    }

    private ModelType createNewModelType() {
        ModelType newModelType = new ModelType();
        newModelType.setName(NAME);
        return newModelType;
    }
}
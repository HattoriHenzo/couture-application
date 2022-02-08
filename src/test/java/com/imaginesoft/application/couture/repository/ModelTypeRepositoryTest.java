package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.ModelType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class ModelTypeRepositoryTest {

    @Autowired
    private ModelTypeRepository repository;

    @Test
    void givenModelType_whenCreateModelType_thenModelTypeExists() {

        ModelType newModelType = createNewModelType();
        ModelType createdModelType = repository.save(newModelType);

        assertAll(
                () -> assertThat(createdModelType).isNotNull(),
                () -> assertThat(createdModelType.getName()).isEqualTo(MODEL_TYPE_NAME)
        );
    }

    @Test
    void givenModelType_whenUpdateModelType_thenModelTypeHasChanged() {

        ModelType newModelType = createNewModelType();

        ModelType modelTypeToUpdate = repository.save(newModelType);
        modelTypeToUpdate.setName(MODEL_TYPE_EDITED_NAME);

        ModelType updatedModelType = repository.save(modelTypeToUpdate);

        assertAll(
                () -> assertThat(updatedModelType.getId()).isEqualTo(modelTypeToUpdate.getId()),
                () -> assertThat(updatedModelType.getName()).isEqualTo(MODEL_TYPE_EDITED_NAME)
        );

    }

    @Test
    void givenModelType_whenDeleteModelType_thenModelTypeDoesNotExists() {

        Optional<ModelType> modelTypeToDelete = repository.findById(MODEL_TYPE_ID);
        repository.delete(modelTypeToDelete.get());
        Optional<ModelType> deletedModelType = repository.findById(MODEL_TYPE_ID);

        assertThat(deletedModelType).isNotPresent();
    }
}
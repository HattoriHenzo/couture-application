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
class ModelTypeRepositoryTest implements WithAssertions {

    @Autowired
    private ModelTypeRepository repository;

    @Test
    void givenModelTypes_whenGettingModelTypes_thenGetAllModelTypes() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenModelType_whenGettingModelTypeById_thenGetModelType() {
        var foundModelType = repository.findById(MODEL_TYPE_ID);
        assumingThat(foundModelType.isPresent(), () -> foundModelType.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(MODEL_TYPE_ID),
                        () -> assertThat(value.getName()).isEqualTo(MODEL_TYPE_NAME)
                )
        ));
    }

    @Test
    void givenModelType_whenCreateModelType_thenModelTypeExists() {
        var newModelType = createNewModelType();
        var createdModelType = repository.save(newModelType);

        assertAll(
                () -> assertThat(createdModelType).isNotNull(),
                () -> assertThat(createdModelType.getId()).isEqualTo(MODEL_TYPE_ID),
                () -> assertThat(createdModelType.getName()).isEqualTo(MODEL_TYPE_NAME)
        );
    }

    @Test
    void givenModelType_whenUpdateModelType_thenModelTypeHasChanged() {
        var modelTypeToUpdate = repository.findById(MODEL_TYPE_ID);
        assumingThat(modelTypeToUpdate.isPresent(), () -> modelTypeToUpdate.ifPresent(
                value -> {
                    value.setName(MODEL_TYPE_EDITED_NAME);
                    var updatedModelType = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedModelType.getId()).isEqualTo(modelTypeToUpdate.get().getId()),
                            () -> assertThat(updatedModelType.getName()).isEqualTo(modelTypeToUpdate.get().getName())
                    );
                }
        ));
    }

    @Test
    void givenModelType_whenDeleteModelType_thenModelTypeDoesNotExists() {
        var modelTypeToDelete = repository.findById(MODEL_TYPE_ID);
        assumingThat(modelTypeToDelete.isPresent(), () -> modelTypeToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedModelType = repository.findById(MODEL_TYPE_ID);
                    assertThat(deletedModelType).isNotPresent();
                }
        ));
    }
}
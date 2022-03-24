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
class MeasureTypeRepositoryTest implements WithAssertions {

    @Autowired
    private MeasureTypeRepository repository;

    @Test
    void givenMeasureTypes_whenGettingMeasureTypes_thenGetAllMeasureTypes() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenMeasureType_whenGettingMeasureTypeById_thenGetMeasureType() {
        var foundMeasureType = repository.findById(MEASURE_TYPE_ID);
        assumingThat(foundMeasureType.isPresent(), () -> foundMeasureType.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(MEASURE_TYPE_ID),
                        () -> assertThat(value.getName()).isEqualTo(MEASURE_TYPE_NAME)
                )
        ));
    }

    @Test
    void givenMeasureType_whenCreateMeasureType_thenMeasureTypeExists() {
        var newMeasureType = createNewMeasureType();
        var createdMeasureType = repository.save(newMeasureType);

        assertAll(
                () -> assertThat(createdMeasureType).isNotNull(),
                () -> assertThat(createdMeasureType.getName()).isEqualTo(MEASURE_TYPE_NAME),
                () -> assertThat(createdMeasureType.getMeasures()).isNotEmpty()
        );
    }

    @Test
    void givenMeasureType_whenUpdateMeasureType_thenMeasureTypeHasChanged() {
        var measureTypeToUpdate = repository.findById(MEASURE_TYPE_ID);
        assumingThat(measureTypeToUpdate.isPresent(), () -> measureTypeToUpdate.ifPresent(
                value -> {
                    value.setName(MEASURE_TYPE_EDITED_NAME);
                    var updatedMeasureType = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedMeasureType.getId()).isEqualTo(MEASURE_TYPE_ID),
                            () -> assertThat(updatedMeasureType.getName()).isEqualTo(MEASURE_TYPE_EDITED_NAME)
                    );
                }
        ));
    }

    @Test
    void givenMeasureType_whenDeleteMeasureType_thenMeasureTypeDoesNotExists() {
        var measureTypeToDelete = repository.findById(MEASURE_TYPE_TO_DELETE);
        assumingThat(measureTypeToDelete.isPresent(), () -> measureTypeToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedMeasureType = repository.findById(MEASURE_TYPE_TO_DELETE);
                    assertThat(deletedMeasureType).isNotPresent();
                }
        ));
    }
}
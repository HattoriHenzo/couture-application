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
class MeasureTypeRepositoryTest implements WithAssertions {

    @Autowired
    private MeasureTypeRepository repository;

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
        measureTypeToUpdate.ifPresent(value -> value.setName(MEASURE_TYPE_EDITED_NAME));
        var updatedMeasureType = repository.save(measureTypeToUpdate.get());

        assertAll(
                () -> assertThat(updatedMeasureType.getId()).isEqualTo(MEASURE_TYPE_ID),
                () -> assertThat(updatedMeasureType.getName()).isEqualTo(MEASURE_TYPE_EDITED_NAME)
        );
    }

    @Test
    void givenMeasureType_whenDeleteMeasureType_thenMeasureTypeDoesNotExists() {

        var measureTypeToDelete = repository.findById(MEASURE_TYPE_TO_DELETE);
        repository.delete(measureTypeToDelete.get());
        var deletedMeasureType = repository.findById(MEASURE_TYPE_TO_DELETE);

        assertThat(deletedMeasureType).isNotPresent();
    }
}
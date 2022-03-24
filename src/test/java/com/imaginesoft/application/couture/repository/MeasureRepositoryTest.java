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
class MeasureRepositoryTest implements WithAssertions {

    @Autowired
    private MeasureRepository repository;

    @Test
    void givenMeasures_whenGettingMeasures_thenGetAllMeasures() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenMeasure_whenGettingMeasureById_thenGetMeasure() {
        var foundMeasure = repository.findById(MEASURE_ID);
        assumingThat(foundMeasure.isPresent(), () -> foundMeasure.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(MEASURE_ID),
                        () -> assertThat(value.getValue()).isEqualTo(MEASURE_VALUE),
                        () -> assertThat(value.getMeasureType().getName()).isEqualTo(MEASURE_TYPE_NAME)
                )
        ));
    }

    @Test
    void givenMeasure_whenCreateMeasure_thenMeasureTypeExists() {
        var newMeasure = createNewMeasure();
        var createdMeasure = repository.save(newMeasure);

        assertAll(
                () -> assertThat(createdMeasure).isNotNull(),
                () -> assertThat(createdMeasure.getValue()).isEqualTo(MEASURE_VALUE)
        );
    }

    @Test
    void givenMeasure_whenUpdateMeasure_thenMeasureTypeHasChanged() {
        var measureToUpdate = repository.findById(MEASURE_ID);
        assumingThat(measureToUpdate.isPresent(), () -> measureToUpdate.ifPresent(
                value -> {
                    value.setValue(MEASURE_EDITED_VALUE);
                    var updatedMeasure = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedMeasure.getId()).isEqualTo(measureToUpdate.get().getId()),
                            () -> assertThat(updatedMeasure.getValue()).isEqualTo(measureToUpdate.get().getValue())
                    );
                }
        ));
    }

    @Test
    void givenMeasure_whenDeleteMeasure_thenMeasureTypeDoesNotExists() {
        var measureToDelete = repository.findById(MEASURE_ID);
        assumingThat(measureToDelete.isPresent(), () -> measureToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedMeasure = repository.findById(MEASURE_ID);
                    assertThat(deletedMeasure).isNotPresent();
                }
        ));
    }
}
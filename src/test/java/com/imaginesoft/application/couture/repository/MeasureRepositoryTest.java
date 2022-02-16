package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Measure;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class MeasureRepositoryTest implements WithAssertions {

    @Autowired
    private MeasureRepository repository;

    @Test
    void givenMeasure_whenCreateMeasure_thenMeasureTypeExists() {

        var newMeasure = createNewMeasure();
        var createdMeasure = repository.save(newMeasure);

        assertAll(
                () -> assertThat(createdMeasure).isNotNull(),
                () -> assertThat(createdMeasure.getValue()).isEqualTo(MEASURE_VALUE),
                () -> assertThat(createdMeasure.getDress()).isNotNull(),
                () -> assertThat(createdMeasure.getMeasureType()).isNotNull()
        );
    }

    @Test
    void givenMeasure_whenUpdateMeasure_thenMeasureTypeHasChanged() {

        var newMeasure = createNewMeasure();

        var measureToUpdate = repository.save(newMeasure);
        measureToUpdate.setValue(MEASURE_EDITED_VALUE);

        var updatedMeasure = repository.save(measureToUpdate);

        assertAll(
                () -> assertThat(updatedMeasure.getId()).isEqualTo(measureToUpdate.getId()),
                () -> assertThat(updatedMeasure.getValue()).isEqualTo(MEASURE_EDITED_VALUE)
        );
    }

    @Test
    void givenMeasure_whenDeleteMeasure_thenMeasureTypeDoesNotExists() {

        var measureToDelete = repository.findById(MEASURE_ID);
        repository.delete(measureToDelete.get());
        var deletedMeasure = repository.findById(MEASURE_ID);

        assertThat(deletedMeasure).isNotPresent();
    }
}
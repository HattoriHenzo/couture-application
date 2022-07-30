package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.repository.MeasureRepository;
import com.imaginesoft.application.couture.service.exception.DomainRecordNotFoundException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeasureServiceTest implements WithAssertions {

    @Mock
    private MeasureRepository repository;

    @InjectMocks
    private MeasureService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenMeasures_whenFindingMeasures_thenFindAllMeasures() throws DomainRecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewMeasures());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenMeasure_whenCreateMeasure_thenMeasureIsCreated() {
        var newMeasure = createNewMeasure();
        when(repository.save(newMeasure)).thenReturn(newMeasure);
        var createdMeasure = underTest.createOrUpdate(newMeasure);

        assertThat(createdMeasure).isNotNull();
    }

    @Test
    void givenMeasure_whenUpdateMeasure_thenMeasureIsUpdated() {
        var measure = createNewMeasure();
        measure.setValue(MEASURE_EDITED_VALUE);
        when(repository.save(measure)).thenReturn(measure);
        when(repository.findById(anyLong())).thenReturn(Optional.of(measure));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(measure)).isNotNull(),
                () -> {
                    var updatedMeasure = underTest.findById(MEASURE_ID);
                    assertAll(
                            () -> assertThat(updatedMeasure.getId()).isEqualTo(measure.getId()),
                            () -> assertThat(updatedMeasure.getValue()).isEqualTo(measure.getValue())
                    );
                }
        );
    }

    @Test
    void givenMeasure_whenDeleteMeasure_thenMeasureIsDeleted() {
        var measureToDelete = createNewMeasure();
        when(repository.findById(anyLong())).thenReturn(Optional.of(measureToDelete));
        var deletedMeasure = underTest.delete(measureToDelete.getId());

        assertThat(deletedMeasure).isNotNull();
    }
}

package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.repository.MeasureTypeRepository;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import com.imaginesoft.application.couture.util.TestDataFactory;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeasureTypeServiceTest implements WithAssertions {

    @Mock
    private MeasureTypeRepository repository;

    @InjectMocks
    private MeasureTypeService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenMeasureTypes_whenFindingMeasureTypes_thenFindAllMeasureTypes() throws RecordNotFoundException {

        when(repository.findAll()).thenReturn(createNewMeasureTypes());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenMeasureType_whenCreateMeasureType_thenMeasureTypeIsCreated() {

        var newMeasureType = createNewMeasureType();
        when(repository.save(newMeasureType)).thenReturn(newMeasureType);
        var createdMeasureType = underTest.createOrUpdate(newMeasureType);

        assertThat(createdMeasureType).isNotNull();
    }

    @Test
    void givenMeasureType_whenNameIsEmpty_thenMeasureTypeThrowException() {

        var newMeasureType = createNewMeasureType();
        newMeasureType.setName("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newMeasureType));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenMeasureType_whenUpdateMeasureType_thenMeasureTypeIsUpdated() {

        var measureType = createNewMeasureType();
        measureType.setName("SHOULDER");
        when(repository.save(measureType)).thenReturn(measureType);
        when(repository.findById(anyLong())).thenReturn(Optional.of(measureType));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(measureType)).isNotNull(),
                () -> {
                    var updatedMeasureType = underTest.findById(1L);
                    assertThat(updatedMeasureType.getName()).isEqualTo(measureType.getName());
                }
        );
    }

    @Test
    void givenMeasureType_whenDeleteMeasureType_thenMeasureTypeIdDeleted() {

        var measureTypeToDelete = createNewMeasureType();
        when(repository.findById(measureTypeToDelete.getId())).thenReturn(Optional.of(measureTypeToDelete));
        var deletedMeasureType = underTest.delete(measureTypeToDelete);

        assertThat(deletedMeasureType).isNotNull();
    }
}

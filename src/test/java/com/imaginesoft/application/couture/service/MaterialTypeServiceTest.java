package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.repository.MaterialTypeRepository;
import com.imaginesoft.application.couture.service.exception.DomainRecordNotFoundException;
import com.imaginesoft.application.couture.service.exception.DomainRulesException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaterialTypeServiceTest implements WithAssertions {

    @Mock
    private MaterialTypeRepository repository;

    @InjectMocks
    private MaterialTypeService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenMaterialTypes_whenFindingMaterialTypes_thenFindAllMaterialTypes() throws DomainRecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewMaterialTypes());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenMaterialType_whenCreateMaterialType_thenMaterialTypeIsCreated() {
        var newMeasureType = createNewMaterialType();
        when(repository.save(newMeasureType)).thenReturn(newMeasureType);
        var createdMeasureType = underTest.createOrUpdate(newMeasureType);

        assertThat(createdMeasureType).isNotNull();
    }

    @Test
    void givenMeasureType_whenNameIsEmpty_thenMeasureTypeThrowException() {
        var newMaterialType = createNewMaterialType();
        newMaterialType.setName(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newMaterialType));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenMeasureType_whenImageIsEmpty_thenMeasureTypeThrowException() {

        var newMeasureType = createNewMaterialType();
        newMeasureType.setImage("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newMeasureType));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenMaterialType_whenUpdateMaterialType_thenMaterialTypeIsUpdated() {

        var materialType = createNewMaterialType();
        materialType.setName("COTTON");
        when(repository.save(materialType)).thenReturn(materialType);
        when(repository.findById(anyLong())).thenReturn(Optional.of(materialType));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(materialType)).isNotNull(),
                () -> {
                    var updatedMeasureType = underTest.findById(1L);
                    assertThat(updatedMeasureType.getName()).isEqualTo(materialType.getName());
                }
        );
    }

    @Test
    void givenMaterialType_whenDeleteMaterialType_thenMaterialTypeIsDeleted() {

        var materialTypeToDelete = createNewMaterialType();
        when(repository.findById(materialTypeToDelete.getId())).thenReturn(Optional.of(materialTypeToDelete));
        var deletedMeasureType = underTest.delete(materialTypeToDelete.getId());

        assertThat(deletedMeasureType).isNotNull();
    }
}

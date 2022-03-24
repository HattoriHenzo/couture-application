package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.repository.ModelTypeRepository;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
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
class ModelTypeServiceTest implements WithAssertions {

    @Mock
    private ModelTypeRepository repository;

    @InjectMocks
    private ModelTypeService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenModelTypes_whenFindingModelTypes_thenFindAllModelTypes() throws RecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewModelTypes());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenModelType_whenCreateModelType_thenModelTypeIsCreated() {
        var newModelType = createNewModelType();
        when(repository.save(newModelType)).thenReturn(newModelType);

        var createdModelType = underTest.createOrUpdate(newModelType);

        assertThat(createdModelType).isNotNull();
    }

    @Test
    void givenModelType_whenNameIsEmpty_thenModelTypeThrowException() {
        var newModelType = createNewModelType();
        newModelType.setName("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newModelType));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenModelType_whenUpdateModelType_thenModelTypeIsUpdated() {
        var modelType = createNewModelType();
        modelType.setName("SHIRT");
        when(repository.save(modelType)).thenReturn(modelType);
        when(repository.findById(anyLong())).thenReturn(Optional.of(modelType));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(modelType)).isNotNull(),
                () -> {
                    var updatedModelType = underTest.findById(1L);
                    assertThat(updatedModelType.getName()).isEqualTo(modelType.getName());
                }
        );
    }

    @Test
    void givenModelType_whenDeleteModelType_thenModelTypeIsDeleted() {
        var modelTypeToDelete = createNewModelType();
        when(repository.findById(anyLong())).thenReturn(Optional.of(modelTypeToDelete));
        var deletedModelType = underTest.delete(modelTypeToDelete.getId());

        assertThat(deletedModelType).isNotNull();
    }
}

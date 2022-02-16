package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.repository.DressTypeRepository;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.createNewDressType;
import static com.imaginesoft.application.couture.util.TestDataFactory.createNewDressTypes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DressTypeServiceTest implements WithAssertions {

    @Mock
    private DressTypeRepository repository;

    @InjectMocks
    private DressTypeService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenDressTypes_whenFindingDressTypes_thenFindAllDressTypes() throws RecordNotFoundException {

        when(repository.findAll()).thenReturn(createNewDressTypes());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenDressType_whenCreateDressType_thenDressTypeIsCreated() {

        var newDressType = createNewDressType();
        when(repository.save(newDressType)).thenReturn(newDressType);
        var createdDressType = underTest.createOrUpdate(newDressType);

        assertThat(createdDressType).isNotNull();
    }

    @Test
    void givenDressType_whenNameIsEmpty_thenDressTypeThrowException() {

        var newDressType = createNewDressType();
        newDressType.setName("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newDressType));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenDressType_whenUpdateDressType_thenDressTypeIsUpdated() {

        var dressType = createNewDressType();
        dressType.setName("SHIRT");
        when(repository.save(dressType)).thenReturn(dressType);
        when(repository.findById(anyLong())).thenReturn(Optional.of(dressType));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(dressType)).isNotNull(),
                () -> {
                    var updatedDressType = underTest.findById(1L);
                    assertThat(updatedDressType.getName()).isEqualTo(dressType.getName());
                }
        );
    }

    @Test
    void givenDressType_whenDeleteDressType_thenDressTypeIdDeleted() {

        var dressTypeToDelete = createNewDressType();
        when(repository.findById(anyLong())).thenReturn(Optional.of(dressTypeToDelete));
        var deletedDressType = underTest.delete(dressTypeToDelete);

        assertThat(deletedDressType).isNotNull();
    }
}

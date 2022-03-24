package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.repository.DressRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DressServiceTest implements WithAssertions {

    @Mock
    private DressRepository repository;

    @InjectMocks
    private DressService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenDresses_whenGettingDresses_thenGetAllDresses() throws RecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewDresses());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenDress_whenCreateDress_thenDressIsCreated() {
        var newDress = createNewDress();
        when(repository.save(any(Dress.class))).thenReturn(newDress);
        var createdDress = underTest.createOrUpdate(newDress);

        assertThat(createdDress).isNotNull();
    }

    @Test
    void givenDress_whenAmountIsNotValid_thenDressThrowException() {
        var newDress = createNewDress();
        newDress.setAmount(DRESS_NEGATIVE_AMOUNT);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newDress));

        assertThat(exception.getMessage()).contains("less than zero");
    }

    @Test
    void givenDress_whenUpdateDress_thenDressIsUpdated() {
        var dress = createNewDress();
        dress.setAmount(DRESS_EDITED_AMOUNT);
        when(repository.save(dress)).thenReturn(dress);
        when(repository.findById(anyLong())).thenReturn(Optional.of(dress));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(dress)).isNotNull(),
                () -> {
                    var updatedDress = underTest.findById(DRESS_ID);
                    assertThat(updatedDress.getAmount()).isEqualTo(dress.getAmount());
                }
        );
    }

    @Test
    void givenDress_whenDeleteDress_thenDressIsDeleted() {
        var dressToDelete = createNewDress();
        when(repository.findById(anyLong())).thenReturn(Optional.of(dressToDelete));
        var deletedDress = underTest.delete(dressToDelete.getId());

        assertThat(underTest.delete(deletedDress.getId())).isNotNull();
    }
}

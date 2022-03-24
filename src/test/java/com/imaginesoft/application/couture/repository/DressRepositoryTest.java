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
class DressRepositoryTest implements WithAssertions {

    @Autowired
    private DressRepository repository;

    @Test
    void givenDresses_whenGettingDresses_thenGetAllDresses() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenDress_whenGettingDressById_thenGetDress() {
        var foundDress = repository.findById(DRESS_ID);
        assumingThat(foundDress.isPresent(), () -> foundDress.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(DRESS_ID),
                        () -> assertThat(value.getAmount()).isEqualTo(DRESS_AMOUNT),
                        () -> assertThat(value.getDressType().getName()).isEqualTo(DRESS_TYPE_NAME),
                        () -> assertThat(value.getMaterialType().getName()).isEqualTo(MATERIAL_TYPE_NAME),
                        () -> assertThat(value.getModelType().getName()).isEqualTo(MODEL_TYPE_NAME)
                )
        ));
    }

    @Test
    void givenDress_whenCreateDress_thenDressExists() {
        var newDress = createNewDress();
        var createdDress = repository.save(newDress);

        assertAll(
                () -> assertThat(createdDress).isNotNull(),
                () -> assertThat(createdDress.getDressType()).isNotNull(),
                () -> assertThat(createdDress.getModelType()).isNotNull(),
                () -> assertThat(createdDress.getMaterialType()).isNotNull(),
                () -> assertThat(createdDress.getMeasures()).isNotEmpty()
        );
    }

    @Test
    void givenDress_whenUpdateDress_thenDressHasChanged() {
        var dressToUpdate = repository.findById(DRESS_ID);
        assumingThat(dressToUpdate.isPresent(), () -> dressToUpdate.ifPresent(
                value -> {
                    value.setAmount(DRESS_EDITED_AMOUNT);
                    var updatedDress = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedDress.getId()).isEqualTo(dressToUpdate.get().getId()),
                            () -> assertThat(updatedDress.getAmount()).isEqualTo(dressToUpdate.get().getAmount())
                    );
                }));
    }

    @Test
    void givenDress_whenDeleteDress_thenDressDoesNotExists() {
        var dressToDelete = repository.findById(DRESS_ID);
        assumingThat(dressToDelete.isPresent(), () -> dressToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedDress = repository.findById(DRESS_ID);
                    assertThat(deletedDress).isNotPresent();
                }
        ));
    }
}
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
class DressTypeRepositoryTest implements WithAssertions {

    @Autowired
    private DressTypeRepository repository;

    @Test
    void givenDressTypes_whenGettingDressType_thenGetAllDressTypes() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenDressType_whenGettingDressTypeById_thenGetDressType() {
        var foundDressType = repository.findById(DRESS_ID);
        assumingThat(foundDressType.isPresent(), () -> foundDressType.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(DRESS_TYPE_ID),
                        () -> assertThat(value.getName()).isEqualTo(DRESS_TYPE_NAME)
                )
        ));
    }

    @Test
    void givenDressType_whenCreateDressType_thenDressTypeExists() {
        var newDressType = createNewDressType();
        var createdDressType = repository.save(newDressType);

        assertAll(
                () -> assertThat(createdDressType).isNotNull(),
                () -> assertThat(createdDressType.getName()).isEqualTo(DRESS_TYPE_NAME)
        );
    }

    @Test
    void givenDressType_whenUpdateDressType_thenDressTypeHasChanged() {
        var dressTypeToUpdate = repository.findById(DRESS_TYPE_ID);
        assumingThat(dressTypeToUpdate.isPresent(), () -> dressTypeToUpdate.ifPresent(
                value -> {
                    value.setName(DRESS_TYPE_EDITED_NAME);
                    var updatedDressType = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedDressType.getId()).isEqualTo(dressTypeToUpdate.get().getId()),
                            () -> assertThat(updatedDressType.getName()).isEqualTo(dressTypeToUpdate.get().getName())
                    );
                }));
    }

    @Test
    void givenDressType_whenDeleteDressType_thenDressTypeDoesNotExists() {
        var dressTypeToDelete = repository.findById(DRESS_TYPE_ID);
        assumingThat(dressTypeToDelete.isPresent(), () -> dressTypeToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedClient = repository.findById(DRESS_TYPE_ID);
                    assertThat(deletedClient).isNotPresent();
                }
        ));
    }
}
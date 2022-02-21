package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class DressTypeRepositoryTest implements WithAssertions {

    private final String NAME = "PANTS";
    private final String EDITED_NAME = "JACKET";

    @Autowired
    private DressTypeRepository repository;

    @Test
    void givenDressTypes_whenGettingDressType_thenGetAllDressTypes() {

        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenDressType_whenCreateDressType_thenDressTypeExists() {

        var newDressType = createNewDressType();
        newDressType.setId(5L);
        var createdDressType = repository.save(newDressType);

        assertAll(
                () -> assertThat(createdDressType).isNotNull(),
                () -> assertThat(createdDressType.getName()).isEqualTo(NAME)
        );
    }

    @Test
    void givenDressType_whenUpdateDressType_thenDressTypeHasChanged() {

        var dressTypeToUpdate = repository.findById(DRESS_TYPE_ID);
        dressTypeToUpdate.ifPresent(value -> value.setName(EDITED_NAME));

        var updatedDressType = repository.save(dressTypeToUpdate.get());

        assertAll(
                () -> assertThat(updatedDressType.getId()).isEqualTo(dressTypeToUpdate.get().getId()),
                () -> assertThat(updatedDressType.getName()).isEqualTo(EDITED_NAME)
        );
    }

    @Test
    void givenDressType_whenDeleteDressType_thenDressTypeDoesNotExists() {

        var dressTypeToDelete = repository.findById(DRESS_TYPE_TO_DELETE);
        repository.delete(dressTypeToDelete.get());
        var deletedClient = repository.findById(DRESS_TYPE_TO_DELETE);

        assertThat(deletedClient).isNotPresent();
    }
}
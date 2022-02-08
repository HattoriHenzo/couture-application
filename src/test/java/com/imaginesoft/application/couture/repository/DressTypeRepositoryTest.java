package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.DressType;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
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

        DressType newDressType = createNewDressType();
        newDressType.setId(5L);
        DressType createdDressType = repository.save(newDressType);

        assertAll(
                () -> assertThat(createdDressType).isNotNull(),
                () -> assertThat(createdDressType.getName()).isEqualTo(NAME)
        );
    }

    @Test
    void givenDressType_whenUpdateDressType_thenDressTypeHasChanged() {

        Optional<DressType> dressTypeToUpdate = repository.findById(DRESS_TYPE_ID);
        dressTypeToUpdate.ifPresent(value -> value.setName(EDITED_NAME));

        DressType updatedDressType = repository.save(dressTypeToUpdate.get());

        assertAll(
                () -> assertThat(updatedDressType.getId()).isEqualTo(dressTypeToUpdate.get().getId()),
                () -> assertThat(updatedDressType.getName()).isEqualTo(EDITED_NAME)
        );
    }

    @Test
    void givenDressType_whenDeleteDressType_thenDressTypeDoesNotExists() {

        Optional<DressType> dressTypeToDelete = repository.findById(DRESS_TYPE_TO_DELETE);
        repository.delete(dressTypeToDelete.get());
        Optional<DressType> deletedClient = repository.findById(DRESS_TYPE_TO_DELETE);

        assertThat(deletedClient).isNotPresent();
    }
}
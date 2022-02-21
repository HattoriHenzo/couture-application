package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.DRESS_ID_TO_DELETE;
import static com.imaginesoft.application.couture.TestDataFactory.createNewDress;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class DressRepositoryTest implements WithAssertions {

    private final Long ID = 1L;
    private final int EDITED_AMOUNT = 1500;

    @Autowired
    private DressRepository repository;

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

        var newDress = createNewDress();

        var dressToUpdate = repository.save(newDress);
        dressToUpdate.setAmount(EDITED_AMOUNT);

        var updatedDress = repository.save(newDress);

        assertAll(
                () -> assertThat(updatedDress.getId()).isEqualTo(dressToUpdate.getId()),
                () -> assertThat(updatedDress.getAmount()).isEqualTo(EDITED_AMOUNT)
        );
    }

    @Test
    void givenDress_whenDeleteDress_thenDressDoesNotExists() {

        var dressToDelete = repository.findById(DRESS_ID_TO_DELETE);
        repository.delete(dressToDelete.get());
        var deletedDress = repository.findById(DRESS_ID_TO_DELETE);

        assertThat(deletedDress).isNotPresent();
    }
}
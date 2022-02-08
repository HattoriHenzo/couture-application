package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Dress;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.createNewDress;

@DataJpaTest
@ActiveProfiles("test")
class DressRepositoryTest implements WithAssertions {

    private final Long ID_1 = 1L;
    private final int EDITED_AMOUNT = 1500;

    @Autowired
    private DressRepository repository;

    @Test
    void givenDress_whenCreateDress_thenDressExists() {

        Dress newDress = createNewDress();
        Dress createdDress = repository.save(newDress);

        Assertions.assertAll(
                () -> assertThat(createdDress).isNotNull(),
                () -> assertThat(createdDress.getDressType()).isNotNull(),
                () -> assertThat(createdDress.getModelType()).isNotNull(),
                () -> assertThat(createdDress.getMaterialType()).isNotNull(),
                () -> assertThat(createdDress.getMeasures()).isNotEmpty()
        );
    }

    @Test
    void givenDress_whenUpdateDress_thenDressHasChanged() {

        Dress newDress = createNewDress();

        Dress dressToUpdate = repository.save(newDress);
        dressToUpdate.setAmount(EDITED_AMOUNT);

        Dress updatedDress = repository.save(newDress);

        Assertions.assertAll(
                () -> assertThat(updatedDress.getId()).isEqualTo(dressToUpdate.getId()),
                () -> assertThat(updatedDress.getAmount()).isEqualTo(EDITED_AMOUNT)
        );
    }

    @Test
    void givenDress_whenDeleteDress_thenDressDoesNotExists() {

        Dress newDress =  createNewDress();
        Dress dressToDelete = repository.save(newDress);
        repository.delete(dressToDelete);
        Optional<Dress> deletedDress = repository.findById(ID_1);

        assertThat(deletedDress).isNotPresent();
    }
}
package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.DressType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DressTypeRepositoryTest {

    private final Long ID_1 = 1L;
    private final Long ID_2 = 2L;

    private final String NAME = "Pants";
    private final String EDITED_NAME = "Jacket";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DressTypeRepository repository;

    @Test
    @Order(1)
    void givenDressType_whenCreateDressType_thenDressTypeExists() {

        DressType newDressType = createNewDressType();
        DressType createdDressType = entityManager.persist(newDressType);

        Assertions.assertAll(
                () -> assertThat(createdDressType).isNotNull(),
                () -> assertThat(createdDressType.getName()).isEqualTo(NAME)
        );
    }

    @Test
    @Order(2)
    void givenDressType_whenUpdateDressType_thenDressTypeHasChanged() {

        DressType newDressType = createNewDressType();

        DressType dressTypeToUpdate = entityManager.persist(newDressType);
        dressTypeToUpdate.setName(EDITED_NAME);

        DressType updatedDressType = repository.save(newDressType);

        Assertions.assertAll(
                () -> assertThat(updatedDressType.getId()).isEqualTo(dressTypeToUpdate.getId()),
                () -> assertThat(updatedDressType.getName()).isEqualTo(EDITED_NAME)
        );
    }

    @Test
    @Order(3)
    void givenDressType_whenDeleteDressType_thenDressTypeDoesNotExists() {

        DressType newDressType = createNewDressType();

        DressType dressTypeToDelete = entityManager.persist(newDressType);
        repository.delete(dressTypeToDelete);

        Optional<DressType> deletedDressType = repository.findById(ID_1);

        assertThat(deletedDressType).isNotPresent();
    }

    private DressType createNewDressType() {
        DressType newDressType = new DressType();
        newDressType.setName(NAME);
        return newDressType;
    }
}
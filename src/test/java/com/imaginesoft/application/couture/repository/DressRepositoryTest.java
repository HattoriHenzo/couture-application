package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DressRepositoryTest {

    private final Long ID_1 = 1L;
    private final int AMOUNT = 1000;
    private final int EDITED_AMOUNT = 1500;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DressRepository repository;

    @Test
    @Order(1)
    void givenDress_whenCreateDress_thenDressExists() {

        Dress newDress = createNewDress();
        Dress createdDress = entityManager.persist(newDress);

        Assertions.assertAll(
                () -> assertThat(createdDress).isNotNull(),
                () -> assertThat(createdDress.getDressType()).isNotNull(),
                () -> assertThat(createdDress.getModelType()).isNotNull(),
                () -> assertThat(createdDress.getMaterialType()).isNotNull(),
                () -> assertThat(createdDress.getMeasures()).isNotEmpty()
        );
    }

    @Test
    @Order(2)
    void givenDress_whenUpdateDress_thenDressHasChanged() {

        Dress newDress = createNewDress();

        Dress dressToUpdate = entityManager.persist(newDress);
        dressToUpdate.setAmount(EDITED_AMOUNT);

        Dress updatedDress = repository.save(newDress);

        Assertions.assertAll(
                () -> assertThat(updatedDress.getId()).isEqualTo(dressToUpdate.getId()),
                () -> assertThat(updatedDress.getAmount()).isEqualTo(EDITED_AMOUNT)
        );

    }

    @Test
    @Order(3)
    void givenDress_whenDeleteDress_thenDressDoesNotExists() {

        Dress newDress =  createNewDress();
        Dress dressToDelete = entityManager.persist(newDress);
        repository.delete(dressToDelete);
        Optional<Dress> deletedDress = repository.findById(ID_1);

        assertThat(deletedDress).isNotPresent();
    }

    private Dress createNewDress() {
        Dress newDress = new Dress();
        newDress.setAmount(AMOUNT);
        newDress.setDressType(new DressType());
        newDress.setModelType(new ModelType());
        newDress.setMaterialType(new MaterialType());
        newDress.setMeasures(createMeasures());
        return newDress;
    }

    private List<Measure> createMeasures() {
        List<Measure> measures = new ArrayList<>();
        measures.add(new Measure());
        measures.add(new Measure());
        return measures;
    }
}
package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.model.MeasureType;
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
class MeasureRepositoryTest {

    private final Long ID_1 = 1L;
    private final Long ID_3 = 3L;
    private final int VALUE = 10;
    private final int EDITED_VALUE = 12;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MeasureRepository repository;

    @Test
    @Order(1)
    void givenMeasure_whenCreateMeasure_thenMeasureTypeExists() {

        Measure newMeasure = createNewMeasure();
        Measure createdMeasure = entityManager.persist(newMeasure);

        Assertions.assertAll(
                () -> assertThat(createdMeasure).isNotNull(),
                () -> assertThat(createdMeasure.getValue()).isEqualTo(VALUE),
                () -> assertThat(createdMeasure.getDress()).isNotNull(),
                () -> assertThat(createdMeasure.getMeasureType()).isNotNull()
        );
    }

    @Test
    @Order(2)
    void givenMeasure_whenUpdateMeasure_thenMeasureTypeHasChanged() {

        Measure newMeasure = createNewMeasure();

        Measure measureToUpdate = entityManager.persist(newMeasure);
        measureToUpdate.setValue(EDITED_VALUE);

        Measure updatedMeasure = repository.save(measureToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedMeasure.getId()).isEqualTo(measureToUpdate.getId()),
                () -> assertThat(updatedMeasure.getValue()).isEqualTo(EDITED_VALUE)
        );
    }

    @Test
    @Order(3)
    void givenMeasure_whenDeleteMeasure_thenMeasureTypeDoesNotExists() {

        Measure newMeasure = createNewMeasure();

        Measure measureToDelete = entityManager.persist(newMeasure);
        repository.delete(measureToDelete);

        Optional<Measure> deletedMeasure = repository.findById(ID_3);

        assertThat(deletedMeasure).isNotPresent();
    }

    private Measure createNewMeasure() {
        Measure newMeasure = new Measure();
        newMeasure.setValue(VALUE);
        newMeasure.setDress(new Dress());
        newMeasure.setMeasureType(new MeasureType());
        return newMeasure;
    }
}
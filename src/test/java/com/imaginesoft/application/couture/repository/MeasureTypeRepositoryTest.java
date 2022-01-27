package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.model.MeasureType;
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
class MeasureTypeRepositoryTest {

    public  final Long ID_3 = 3L;
    private final String NAME = "Shoulder";
    private final String EDITED_NAME = "Harm";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MeasureTypeRepository repository;

    @Test
    @Order(1)
    void givenMeasureType_whenCreateMeasureType_thenMeasureTypeExists() {

        MeasureType newMeasureType = createNewMeasureType();
        MeasureType createdMeasureType = entityManager.persist(newMeasureType);

        Assertions.assertAll(
                () -> assertThat(createdMeasureType).isNotNull(),
                () -> assertThat(createdMeasureType.getName()).isEqualTo(NAME),
                () -> assertThat(createdMeasureType.getMeasures()).isNotEmpty()
        );
    }

    @Test
    @Order(2)
    void givenMeasureType_whenUpdateMeasureType_thenMeasureTypeHasChanged() {

        MeasureType newMeasureType = createNewMeasureType();

        MeasureType measureTypeToUpdate = entityManager.persist(newMeasureType);
        measureTypeToUpdate.setName(EDITED_NAME);

        MeasureType updatedMeasureType = repository.save(measureTypeToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedMeasureType.getId()).isEqualTo(measureTypeToUpdate.getId()),
                () -> assertThat(updatedMeasureType.getName()).isEqualTo(EDITED_NAME)
        );
    }

    @Test
    @Order(3)
    void givenMeasureType_whenDeleteMeasureType_thenMeasureTypeDoesNotExists() {

        MeasureType newMeasureType = createNewMeasureType();

        MeasureType measureTypeToDelete = entityManager.persist(newMeasureType);
        repository.delete(measureTypeToDelete);

        Optional<MeasureType> deletedMeasureType = repository.findById(ID_3);

        assertThat(deletedMeasureType).isNotPresent();
    }

    private MeasureType createNewMeasureType() {
        MeasureType newMeasureType = new MeasureType();
        newMeasureType.setName(NAME);
        newMeasureType.setMeasures(createMeasures());
        return newMeasureType;
    }

    private List<Measure> createMeasures() {
        List<Measure> measures = new ArrayList<>();
        measures.add(new Measure());
        measures.add(new Measure());
        return measures;
    }
}
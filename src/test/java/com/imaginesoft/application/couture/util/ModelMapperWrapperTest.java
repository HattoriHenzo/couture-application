package com.imaginesoft.application.couture.util;

import com.imaginesoft.application.couture.configuration.TestApplicationConfig;
import com.imaginesoft.application.couture.dto.DummyModelDto;
import com.imaginesoft.application.couture.model.DummyModel;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringJUnitWebConfig(TestApplicationConfig.class)
class ModelMapperWrapperTest implements WithAssertions {

    private final MapperWrapper mapperWrapper;

    @Autowired
    public ModelMapperWrapperTest(MapperWrapper mapperWrapper) {
        this.mapperWrapper = mapperWrapper;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenModel_whenMapModelToDto_thenReturnsDto() {

        DummyModel model = new DummyModel();
        model.setId(1);
        model.setName("DUMMY_NAME");
        model.setAge(25);

        DummyModelDto modelDto = mapperWrapper.performMapping(model, DummyModelDto.class);

        assertAll(
                () -> assertThat(modelDto.getId()).isEqualTo(String.valueOf(model.getId())),
                () -> assertThat(modelDto.getName()).isEqualTo(model.getName()),
                () -> assertThat(modelDto.getAge()).isEqualTo(String.valueOf(model.getAge()))
        );

    }

    @Test
    void givenDto_whenMapDtoToModel_thenReturnsModel() {

        DummyModelDto modelDto = new DummyModelDto();
        modelDto.setId("1");
        modelDto.setName("DUMMY_NAME");
        modelDto.setAge("25");

        DummyModel model = mapperWrapper.performMapping(modelDto, DummyModel.class);

        assertAll(
                () -> assertThat(model.getId()).isEqualTo(Integer.valueOf(modelDto.getId())),
                () -> assertThat(model.getName()).isEqualTo(modelDto.getName()),
                () -> assertThat(model.getAge()).isEqualTo(Integer.valueOf(modelDto.getAge()))
        );
    }

}

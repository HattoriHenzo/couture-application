package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.service.ModelTypeService;
import com.imaginesoft.application.couture.util.DataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.Clock;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ModelTypeController.class)
class ModelTypeControllerTest extends BaseControllerTest {

    @MockBean
    private ModelTypeService serviceMock;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {

        var modelType = createNewModelType();
        var modelTypeDto = createNewModelTypeDto();

        when(serviceMock.findById(anyLong())).thenReturn(modelType);
        when(mapperMock.performMapping(modelType, ModelTypeDto.class)).thenReturn(modelTypeDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(DataFactory.API_V1 + "/dress-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }


}

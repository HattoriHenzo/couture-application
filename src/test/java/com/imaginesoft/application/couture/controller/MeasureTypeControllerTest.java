package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.service.MeasureTypeService;
import com.imaginesoft.application.couture.util.DataFactory;
import com.imaginesoft.application.couture.util.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.Clock;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeasureTypeController.class)
class MeasureTypeControllerTest extends BaseControllerTest {

    @MockBean
    private MeasureTypeService serviceMock;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {

        var measureType = createNewMeasureType();
        var measureTypeDto = createNewMeasureTypeDto();

        when(serviceMock.findById(anyLong())).thenReturn(measureType);
        when(mapperMock.performMapping(measureType, MeasureTypeDto.class)).thenReturn(measureTypeDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(DataFactory.API_V1 + "/measure-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {

        when(serviceMock.findById(anyLong())).thenReturn(new MeasureType());

        mockMvc.perform(get(DataFactory.API_V1 + "/measure-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {

        when(serviceMock.findById(anyLong())).thenReturn(new MeasureType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {

        var measureType = createNewMeasureType();
        var measureTypes = createNewMeasureTypes();
        var measureTypeDto = createNewMeasureTypeDto();

        when(serviceMock.findAll()).thenReturn(measureTypes);
        when(mapperMock.performMapping(measureType, MeasureTypeDto.class)).thenReturn(measureTypeDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(DataFactory.API_V1 + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(greaterThan(0)))
                );
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {

        when(serviceMock.findAll()).thenReturn(createNewMeasureTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenMeasureType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {

        var measureTypeRequest = BAD_BODY;
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapperMock.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(serviceMock.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapperMock.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(DataFactory.API_V1 + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMeasureType_whenCallCreate_thenReturns_200_OK() throws Exception {

        var measureTypeRequest = createNewMeasureTypeDto();
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapperMock.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(serviceMock.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapperMock.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(DataFactory.API_V1 + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureTypeRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("created")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenMeasureType_whenCallUpdate_thenReturns_200_OK() throws Exception {

        var measureTypeRequest = createNewMeasureTypeDto();
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapperMock.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(serviceMock.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapperMock.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(DataFactory.API_V1 + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureTypeRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("updated")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenMeasureType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {

        var measureTypeRequest = BAD_BODY;
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapperMock.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(serviceMock.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapperMock.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(DataFactory.API_V1 + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMeasureType_whenCallDelete_thenReturns_200_OK() throws Exception {

        var measureTypeToDelete = createNewMeasureType();
        var deletedMeasureType = measureTypeToDelete;
        var measureTypeResponse = createNewMeasureTypeDto();

        when(serviceMock.findById(ID)).thenReturn(measureTypeToDelete);
        when(serviceMock.delete(measureTypeToDelete)).thenReturn(deletedMeasureType);
        when(mapperMock.performMapping(deletedMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(DataFactory.API_V1 + "/measure-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("deleted")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenMeasureType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {

        var measureTypeToDelete = createNewMeasureType();
        var deletedMeasureType = measureTypeToDelete;
        var measureTypeResponse = createNewMeasureTypeDto();

        when(serviceMock.findById(ID)).thenReturn(measureTypeToDelete);
        when(serviceMock.delete(measureTypeToDelete)).thenReturn(deletedMeasureType);
        when(mapperMock.performMapping(deletedMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(DataFactory.API_V1 + "/measure-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMeasureType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {

        var measureTypeToDelete = createNewMeasureType();
        var deletedMeasureType = measureTypeToDelete;
        var measureTypeResponse = createNewMeasureTypeDto();

        when(serviceMock.findById(ID)).thenReturn(null);
        when(serviceMock.delete(measureTypeToDelete)).thenReturn(deletedMeasureType);
        when(mapperMock.performMapping(deletedMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(DataFactory.API_V1 + "/measure-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

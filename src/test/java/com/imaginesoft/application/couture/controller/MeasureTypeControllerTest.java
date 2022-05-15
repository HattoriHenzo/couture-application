package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.service.MeasureTypeService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.Clock;

import static com.imaginesoft.application.couture.TestDataFactory.*;
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
    private MeasureTypeService service;

    @BeforeEach
    void setUp() {

    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var measureType = createNewMeasureType();
        var measureTypeDto = createNewMeasureTypeDto();

        when(service.findById(anyLong())).thenReturn(measureType);
        when(mapper.performMapping(measureType, MeasureTypeDto.class)).thenReturn(measureTypeDto);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(new MeasureType());

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(new MeasureType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var measureType = createNewMeasureType();
        var measureTypes = createNewMeasureTypes();
        var measureTypeDto = createNewMeasureTypeDto();

        when(service.findAll()).thenReturn(measureTypes);
        when(mapper.performMapping(measureType, MeasureTypeDto.class)).thenReturn(measureTypeDto);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(greaterThan(0)))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {
        when(service.findAll()).thenReturn(createNewMeasureTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var measureTypeRequest = BAD_BODY;
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapper.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(service.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapper.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallCreate_thenReturns_200_OK() throws Exception {
        var measureTypeRequest = createNewMeasureTypeDto();
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapper.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(service.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapper.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types")
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

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var measureTypeRequest = createNewMeasureTypeDto();
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapper.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(service.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapper.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types")
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

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var measureTypeRequest = BAD_BODY;
        var measureTypeToCreate = createNewMeasureType();
        var createdMeasureType = createNewMeasureType();
        var measureTypeResponse = createNewMeasureTypeDto();

        when(mapper.performMapping(measureTypeRequest, MeasureType.class)).thenReturn(measureTypeToCreate);
        when(service.createOrUpdate(measureTypeToCreate)).thenReturn(createdMeasureType);
        when(mapper.performMapping(createdMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallDelete_thenReturns_200_OK() throws Exception {
        var measureTypeToDelete = createNewMeasureType();
        var deletedMeasureType = measureTypeToDelete;
        var measureTypeResponse = createNewMeasureTypeDto();

        when(service.findById(ID)).thenReturn(measureTypeToDelete);
        when(service.delete(measureTypeToDelete.getId())).thenReturn(deletedMeasureType);
        when(mapper.performMapping(deletedMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("deleted")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var measureTypeToDelete = createNewMeasureType();
        var deletedMeasureType = measureTypeToDelete;
        var measureTypeResponse = createNewMeasureTypeDto();

        when(service.findById(ID)).thenReturn(measureTypeToDelete);
        when(service.delete(measureTypeToDelete.getId())).thenReturn(deletedMeasureType);
        when(mapper.performMapping(deletedMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMeasureType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var measureTypeToDelete = createNewMeasureType();
        var deletedMeasureType = measureTypeToDelete;
        var measureTypeResponse = createNewMeasureTypeDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(measureTypeToDelete.getId())).thenReturn(deletedMeasureType);
        when(mapper.performMapping(deletedMeasureType, MeasureTypeDto.class)).thenReturn(measureTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/measure-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

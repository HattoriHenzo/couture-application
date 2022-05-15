package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.MeasureDto;
import com.imaginesoft.application.couture.model.Measure;
import com.imaginesoft.application.couture.service.MeasureService;
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

@WebMvcTest(MeasureController.class)
class MeasureControllerTest extends BaseControllerTest {

    @MockBean
    private MeasureService service;

    @BeforeEach
    void setUp() {
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var measure = createNewMeasure();
        var measureResponse = createNewMeasureDto();

        when(service.findById(anyLong())).thenReturn(measure);
        when(mapper.performMapping(measure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/measures/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(new Measure());

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/measures/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(new Measure());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var measure = createNewMeasure();
        var measures = createNewMeasures();
        var measureResponse = createNewMeasureDto();

        when(service.findAll()).thenReturn(measures);
        when(mapper.performMapping(measure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/measures")
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
        when(service.findAll()).thenReturn(createNewMeasures());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var measureRequest = BAD_BODY;
        var measureToCreate = createNewMeasure();
        var createdMeasure = createNewMeasure();
        var measureResponse = createNewMeasureDto();

        when(mapper.performMapping(measureRequest, Measure.class)).thenReturn(measureToCreate);
        when(service.createOrUpdate(measureToCreate)).thenReturn(createdMeasure);
        when(mapper.performMapping(createdMeasure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/measures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallCreate_thenReturns_200_OK() throws Exception {
        var measureRequest = createNewMeasureDto();
        var measureToCreate = createNewMeasure();
        var createdMeasure = createNewMeasure();
        var measureResponse = createNewMeasureDto();

        when(mapper.performMapping(measureRequest, Measure.class)).thenReturn(measureToCreate);
        when(service.createOrUpdate(measureToCreate)).thenReturn(createdMeasure);
        when(mapper.performMapping(createdMeasure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/measures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureRequest)))
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
        var measureRequest = createNewMeasureDto();
        var measureToCreate = createNewMeasure();
        var createdMeasure = createNewMeasure();
        var measureResponse = createNewMeasureDto();

        when(mapper.performMapping(measureRequest, Measure.class)).thenReturn(measureToCreate);
        when(service.createOrUpdate(measureToCreate)).thenReturn(createdMeasure);
        when(mapper.performMapping(createdMeasure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/measures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureRequest)))
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
    void givenMaterialType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var measureRequest = BAD_BODY;
        var measureToCreate = createNewMeasure();
        var createdMeasure = createNewMeasure();
        var measureResponse = createNewMeasureDto();

        when(mapper.performMapping(measureRequest, Measure.class)).thenReturn(measureToCreate);
        when(service.createOrUpdate(measureToCreate)).thenReturn(createdMeasure);
        when(mapper.performMapping(createdMeasure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/measures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measureRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallDelete_thenReturns_200_OK() throws Exception {
        var measureToDelete = createNewMeasure();
        var deletedMaterialType = measureToDelete;
        var measureResponse = createNewMeasureDto();

        when(service.findById(ID)).thenReturn(measureToDelete);
        when(service.delete(measureToDelete.getId())).thenReturn(deletedMaterialType);
        when(mapper.performMapping(deletedMaterialType, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/measures/{id}", ID)
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
    void givenMaterialType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var measureToDelete = createNewMeasure();
        var deletedMeasure = measureToDelete;
        var measureResponse = createNewMeasureDto();

        when(service.findById(ID)).thenReturn(measureToDelete);
        when(service.delete(measureToDelete.getId())).thenReturn(deletedMeasure);
        when(mapper.performMapping(deletedMeasure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/measures/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var measureToDelete = createNewMeasure();
        var deletedMeasure = measureToDelete;
        var measureResponse = createNewMeasureDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(measureToDelete.getId())).thenReturn(deletedMeasure);
        when(mapper.performMapping(deletedMeasure, MeasureDto.class)).thenReturn(measureResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/measures/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}
package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.model.MeasureType;
import com.imaginesoft.application.couture.model.ModelType;
import com.imaginesoft.application.couture.service.ModelTypeService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.Clock;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/model-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {

        when(serviceMock.findById(anyLong())).thenReturn(new ModelType());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/model-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {

        when(serviceMock.findById(anyLong())).thenReturn(new ModelType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {

        var modelType = createNewModelType();
        var modelTypes = createNewModelTypes();
        var modelTypeDto = createNewModelTypeDto();

        when(serviceMock.findAll()).thenReturn(modelTypes);
        when(mapperMock.performMapping(modelType, ModelTypeDto.class)).thenReturn(modelTypeDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/model-types")
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

        when(serviceMock.findAll()).thenReturn(createNewModelTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenModelType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {

        var modelTypeRequest = BAD_BODY;
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapperMock.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(serviceMock.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapperMock.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/model-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenModelType_whenCallCreate_thenReturns_200_OK() throws Exception {

        var modelTypeRequest = createNewModelTypeDto();
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapperMock.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(serviceMock.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapperMock.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/model-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelTypeRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("created")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenModelType_whenCallUpdate_thenReturns_200_OK() throws Exception {

        var modelTypeRequest = createNewModelTypeDto();
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapperMock.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(serviceMock.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapperMock.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/model-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelTypeRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("updated")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenModelType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {

        var modelTypeRequest = BAD_BODY;
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapperMock.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(serviceMock.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapperMock.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/model-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenModelType_whenCallDelete_thenReturns_200_OK() throws Exception {

        var modelTypeToDelete = createNewModelType();
        var deletedModelType = modelTypeToDelete;
        var modelTypeResponse = createNewModelTypeDto();

        when(serviceMock.findById(ID)).thenReturn(modelTypeToDelete);
        when(serviceMock.delete(modelTypeToDelete)).thenReturn(deletedModelType);
        when(mapperMock.performMapping(deletedModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/model-types/{id}", ID)
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
    void givenModelType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var modelTypeToDelete = createNewModelType();
        var deletedModelType = modelTypeToDelete;
        var modelTypeResponse = createNewModelTypeDto();

        when(serviceMock.findById(ID)).thenReturn(modelTypeToDelete);
        when(serviceMock.delete(modelTypeToDelete)).thenReturn(deletedModelType);
        when(mapperMock.performMapping(deletedModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/model-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenModelType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var modelTypeToDelete = createNewModelType();
        var deletedModelType = modelTypeToDelete;
        var modelTypeResponse = createNewModelTypeDto();

        when(serviceMock.findById(ID)).thenReturn(null);
        when(serviceMock.delete(modelTypeToDelete)).thenReturn(deletedModelType);
        when(mapperMock.performMapping(deletedModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/model-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

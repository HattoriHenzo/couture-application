package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.model.ModelType;
import com.imaginesoft.application.couture.service.ModelTypeService;
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

@WebMvcTest(ModelTypeController.class)
class ModelTypeControllerTest extends BaseControllerTest {

    @MockBean
    private ModelTypeService service;

    @BeforeEach
    void setUp() {

    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var modelType = createNewModelType();
        var modelTypeDto = createNewModelTypeDto();

        when(service.findById(anyLong())).thenReturn(modelType);
        when(mapper.performMapping(modelType, ModelTypeDto.class)).thenReturn(modelTypeDto);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(new ModelType());

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(new ModelType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var modelType = createNewModelType();
        var modelTypes = createNewModelTypes();
        var modelTypeDto = createNewModelTypeDto();

        when(service.findAll()).thenReturn(modelTypes);
        when(mapper.performMapping(modelType, ModelTypeDto.class)).thenReturn(modelTypeDto);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
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
        when(service.findAll()).thenReturn(createNewModelTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenModelType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var modelTypeRequest = BAD_BODY;
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapper.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(service.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapper.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenModelType_whenCallCreate_thenReturns_200_OK() throws Exception {
        var modelTypeRequest = createNewModelTypeDto();
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapper.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(service.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapper.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
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

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenModelType_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var modelTypeRequest = createNewModelTypeDto();
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapper.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(service.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapper.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
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

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenModelType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var modelTypeRequest = BAD_BODY;
        var modelTypeToCreate = createNewModelType();
        var createdModelType = createNewModelType();
        var modelTypeResponse = createNewModelTypeDto();

        when(mapper.performMapping(modelTypeRequest, ModelType.class)).thenReturn(modelTypeToCreate);
        when(service.createOrUpdate(modelTypeToCreate)).thenReturn(createdModelType);
        when(mapper.performMapping(createdModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/model-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenModelType_whenCallDelete_thenReturns_200_OK() throws Exception {
        var modelTypeToDelete = createNewModelType();
        var deletedModelType = modelTypeToDelete;
        var modelTypeResponse = createNewModelTypeDto();

        when(service.findById(ID)).thenReturn(modelTypeToDelete);
        when(service.delete(modelTypeToDelete.getId())).thenReturn(deletedModelType);
        when(mapper.performMapping(deletedModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/{id}", ID)
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
    void givenModelType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var modelTypeToDelete = createNewModelType();
        var deletedModelType = modelTypeToDelete;
        var modelTypeResponse = createNewModelTypeDto();

        when(service.findById(ID)).thenReturn(modelTypeToDelete);
        when(service.delete(modelTypeToDelete.getId())).thenReturn(deletedModelType);
        when(mapper.performMapping(deletedModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenModelType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var modelTypeToDelete = createNewModelType();
        var deletedModelType = modelTypeToDelete;
        var modelTypeResponse = createNewModelTypeDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(modelTypeToDelete.getId()   )).thenReturn(deletedModelType);
        when(mapper.performMapping(deletedModelType, ModelTypeDto.class)).thenReturn(modelTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/model-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

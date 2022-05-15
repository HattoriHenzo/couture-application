package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.MaterialTypeDto;
import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.service.MaterialTypeService;
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

@WebMvcTest(MaterialTypeController.class)
class MaterialTypeControllerTest extends BaseControllerTest {

    @MockBean
    private MaterialTypeService service;

    @BeforeEach
    void setUp() {

    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var materialType = createNewMaterialType();
        var materialTypeResponse = createNewMaterialTypeDto();

        when(service.findById(anyLong())).thenReturn(materialType);
        when(mapper.performMapping(materialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/material-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(new MaterialType());

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/material-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(new MaterialType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var materialType = createNewMaterialType();
        var materialTypes = createNewMaterialTypes();
        var materialTypeResponse = createNewMaterialTypeDto();

        when(service.findAll()).thenReturn(materialTypes);
        when(mapper.performMapping(materialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/material-types")
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
        when(service.findAll()).thenReturn(createNewMaterialTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var materialTypeRequest = BAD_BODY;
        var materialTypeToCreate = createNewMaterialType();
        var createdMaterialType = createNewMaterialType();
        var materialTypeResponse = createNewMaterialTypeDto();

        when(mapper.performMapping(materialTypeRequest, MaterialType.class)).thenReturn(materialTypeToCreate);
        when(service.createOrUpdate(materialTypeToCreate)).thenReturn(createdMaterialType);
        when(mapper.performMapping(createdMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/material-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materialTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallCreate_thenReturns_200_OK() throws Exception {
        var materialTypeRequest = createNewMaterialTypeDto();
        var materialTypeToCreate = createNewMaterialType();
        var createdMaterialType = createNewMaterialType();
        var materialTypeResponse = createNewMaterialTypeDto();

        when(mapper.performMapping(materialTypeRequest, MaterialType.class)).thenReturn(materialTypeToCreate);
        when(service.createOrUpdate(materialTypeToCreate)).thenReturn(createdMaterialType);
        when(mapper.performMapping(createdMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/material-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materialTypeRequest)))
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
        var materialTypeRequest = createNewMaterialTypeDto();
        var materialTypeToCreate = createNewMaterialType();
        var createdMaterialType = createNewMaterialType();
        var materialTypeResponse = createNewMaterialTypeDto();

        when(mapper.performMapping(materialTypeRequest, MaterialType.class)).thenReturn(materialTypeToCreate);
        when(service.createOrUpdate(materialTypeToCreate)).thenReturn(createdMaterialType);
        when(mapper.performMapping(createdMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/material-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materialTypeRequest)))
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
        var materialTypeRequest = BAD_BODY;
        var materialTypeToCreate = createNewMaterialType();
        var createdMaterialType = createNewMaterialType();
        var materialTypeResponse = createNewMaterialTypeDto();

        when(mapper.performMapping(materialTypeRequest, MaterialType.class)).thenReturn(materialTypeToCreate);
        when(service.createOrUpdate(materialTypeToCreate)).thenReturn(createdMaterialType);
        when(mapper.performMapping(createdMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/material-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materialTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallDelete_thenReturns_200_OK() throws Exception {
        var materialTypeToDelete = createNewMaterialType();
        var deletedMaterialType = materialTypeToDelete;
        var materialTypeResponse = createNewMaterialTypeDto();

        when(service.findById(ID)).thenReturn(materialTypeToDelete);
        when(service.delete(materialTypeToDelete.getId())).thenReturn(deletedMaterialType);
        when(mapper.performMapping(deletedMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/material-types/{id}", ID)
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
        var materialTypeToDelete = createNewMaterialType();
        var deletedMaterialType = materialTypeToDelete;
        var materialTypeResponse = createNewMaterialTypeDto();

        when(service.findById(ID)).thenReturn(materialTypeToDelete);
        when(service.delete(materialTypeToDelete.getId())).thenReturn(deletedMaterialType);
        when(mapper.performMapping(deletedMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/material-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenMaterialType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var materialTypeToDelete = createNewMaterialType();
        var deletedMaterialType = materialTypeToDelete;
        var materialTypeResponse = createNewMaterialTypeDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(materialTypeToDelete.getId())).thenReturn(deletedMaterialType);
        when(mapper.performMapping(deletedMaterialType, MaterialTypeDto.class)).thenReturn(materialTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/material-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

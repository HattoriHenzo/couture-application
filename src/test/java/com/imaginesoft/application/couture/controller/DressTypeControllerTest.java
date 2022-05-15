package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.model.DressType;
import com.imaginesoft.application.couture.service.DressTypeService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.TestDataFactory;
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

@WebMvcTest(DressTypeController.class)
class DressTypeControllerTest extends BaseControllerTest {

    @MockBean
    private DressTypeService service;

    @BeforeEach
    void setUp() {

    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var dressType = createNewDressType();
        var dressTypeResponse = createNewDressTypeDto();

        when(service.findById(anyLong())).thenReturn(dressType);
        when(mapper.performMapping(dressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewDressType());

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewDressType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var dressType = createNewDressType();
        var dressTypes = createNewDressTypes();
        var dressTypeResponse = createNewDressTypeDto();

        when(service.findAll()).thenReturn(dressTypes);
        when(mapper.performMapping(dressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {
        when(service.findAll()).thenReturn(TestDataFactory.createNewDressTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenDressType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var dressTypeRequest = BAD_BODY;
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var createdDressType = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapper.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(service.createOrUpdate(dressTypeToCreate)).thenReturn(createdDressType);
        when(mapper.performMapping(createdDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenDressType_whenCallCreate_thenReturns_200_OK() throws Exception {
        var dressTypeRequest = TestDataFactory.createNewDressTypeDto();
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var dressTypeClient = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapper.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(service.createOrUpdate(dressTypeToCreate)).thenReturn(dressTypeClient);
        when(mapper.performMapping(dressTypeClient, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressTypeRequest)))
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
    void givenDressType_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var dressTypeRequest = TestDataFactory.createNewDressTypeDto();
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var createdDressType = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapper.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(service.createOrUpdate(dressTypeToCreate)).thenReturn(createdDressType);
        when(mapper.performMapping(createdDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressTypeRequest)))
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
    void givenDressType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var dressTypeRequest = BAD_BODY;
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var createdDressType = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapper.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(service.createOrUpdate(dressTypeToCreate)).thenReturn(createdDressType);
        when(mapper.performMapping(createdDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenClient_whenCallDelete_thenReturns_200_OK() throws Exception {
        var dressTypeToDelete = createNewDressType();
        var deletedDressType = dressTypeToDelete;
        var dressTypeResponse = createNewDressTypeDto();

        when(service.findById(ID)).thenReturn(dressTypeToDelete);
        when(service.delete(dressTypeToDelete.getId())).thenReturn(deletedDressType);
        when(mapper.performMapping(deletedDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/{id}", ID)
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
    void givenDressType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var dressTypeToDelete = createNewDressType();
        var deletedDressType = dressTypeToDelete;
        var dressTypeResponse = createNewDressTypeDto();

        when(service.findById(ID)).thenReturn(dressTypeToDelete);
        when(service.delete(dressTypeToDelete.getId())).thenReturn(deletedDressType);
        when(mapper.performMapping(deletedDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenDressType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var dressTypeToDelete = createNewDressType();
        var deletedDressType = dressTypeToDelete;
        var dressTypeResponse = createNewDressTypeDto();

        when(service.findById(DRESS_TYPE_ID)).thenReturn(null);
        when(service.delete(dressTypeToDelete.getId())).thenReturn(deletedDressType);
        when(mapper.performMapping(deletedDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/dress-types/{id}", DRESS_TYPE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

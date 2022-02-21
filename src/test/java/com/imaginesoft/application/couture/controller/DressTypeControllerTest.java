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
    private DressTypeService serviceMock;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {

        var dressType = createNewDressType();
        var dressTypeDto = createNewDressTypeDto();

        when(serviceMock.findById(anyLong())).thenReturn(dressType);
        when(mapperMock.performMapping(dressType, DressTypeDto.class)).thenReturn(dressTypeDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/dress-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {

        when(serviceMock.findById(anyLong())).thenReturn(new DressType());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/dress-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {

        when(serviceMock.findById(anyLong())).thenReturn(new DressType());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {

        var dressType = TestDataFactory.createNewDressType();
        var dressTypes = TestDataFactory.createNewDressTypes();
        var dressTypeDto = TestDataFactory.createNewDressTypeDto();

        when(serviceMock.findAll()).thenReturn(dressTypes);
        when(mapperMock.performMapping(dressType, DressTypeDto.class)).thenReturn(dressTypeDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {

        when(serviceMock.findAll()).thenReturn(TestDataFactory.createNewDressTypes());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenDressType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {

        var dressTypeRequest = BAD_BODY;
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var createdDressType = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapperMock.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(serviceMock.createOrUpdate(dressTypeToCreate)).thenReturn(createdDressType);
        when(mapperMock.performMapping(createdDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenDressType_whenCallCreate_thenReturns_200_OK() throws Exception {

        var dressTypeRequest = TestDataFactory.createNewDressTypeDto();
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var dressTypeClient = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapperMock.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(serviceMock.createOrUpdate(dressTypeToCreate)).thenReturn(dressTypeClient);
        when(mapperMock.performMapping(dressTypeClient, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/dress-types")
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

    @Test
    void givenDressType_whenCallUpdate_thenReturns_200_OK() throws Exception {

        var dressTypeRequest = TestDataFactory.createNewDressTypeDto();
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var createdDressType = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapperMock.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(serviceMock.createOrUpdate(dressTypeToCreate)).thenReturn(createdDressType);
        when(mapperMock.performMapping(createdDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/dress-types")
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

    @Test
    void givenDressType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {

        var dressTypeRequest = BAD_BODY;
        var dressTypeToCreate = TestDataFactory.createNewDressType();
        var createdDressType = TestDataFactory.createNewDressType();
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(mapperMock.performMapping(dressTypeRequest, DressType.class)).thenReturn(dressTypeToCreate);
        when(serviceMock.createOrUpdate(dressTypeToCreate)).thenReturn(createdDressType);
        when(mapperMock.performMapping(createdDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/dress-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressTypeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_200_OK() throws Exception {

        var dressTypeToDelete = TestDataFactory.createNewDressType();
        var deletedDressType = dressTypeToDelete;
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(serviceMock.findById(ID)).thenReturn(dressTypeToDelete);
        when(serviceMock.delete(dressTypeToDelete)).thenReturn(deletedDressType);
        when(mapperMock.performMapping(deletedDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/dress-types/{id}", ID)
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
    void givenDressType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {

        var dressTypeToDelete = TestDataFactory.createNewDressType();
        var deletedDressType = dressTypeToDelete;
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(serviceMock.findById(ID)).thenReturn(dressTypeToDelete);
        when(serviceMock.delete(dressTypeToDelete)).thenReturn(deletedDressType);
        when(mapperMock.performMapping(deletedDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/dress-types/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenDressType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {

        var dressTypeToDelete = TestDataFactory.createNewDressType();
        var deletedDressType = dressTypeToDelete;
        var dressTypeResponse = TestDataFactory.createNewDressTypeDto();

        when(serviceMock.findById(ID)).thenReturn(null);
        when(serviceMock.delete(dressTypeToDelete)).thenReturn(deletedDressType);
        when(mapperMock.performMapping(deletedDressType, DressTypeDto.class)).thenReturn(dressTypeResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/dress-types/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

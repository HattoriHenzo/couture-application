package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.DressDto;
import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.service.DressService;
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

@WebMvcTest(DressController.class)
class DressControllerTest extends BaseControllerTest {

    @MockBean
    private DressService service;

    @BeforeEach
    void setUp() {

    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var newDress = createNewDress();
        var newDressResponse = createNewDressDto();

        when(service.findById(anyLong())).thenReturn(newDress);
        when(mapper.performMapping(newDress, DressDto.class)).thenReturn(newDressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/dresses/{id}", ID)
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
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewDress());

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/dresses/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewDress());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var dress = createNewDress();
        var dresses = createNewDresses();
        var dressResponse = createNewDressDto();

        when(service.findAll()).thenReturn(dresses);
        when(mapper.performMapping(dress, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_APPLICATION + "/dresses")
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
    void givenAll_whenCallFindAll_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findAll()).thenReturn(createNewDresses());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenClient_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var dressRequest = BAD_BODY;
        var dressToCreate = createNewDress();
        var createdDress = createNewDress();
        var dressResponse = createNewDressDto();

        when(mapper.performMapping(dressRequest, Dress.class)).thenReturn(dressToCreate);
        when(service.createOrUpdate(dressToCreate)).thenReturn(createdDress);
        when(mapper.performMapping(createdDress, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/dresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenClient_whenCallCreate_thenReturns_200_OK() throws Exception {
        var dressRequest = createNewDressDto();
        var dressToCreate = createNewDress();
        var createdDress = createNewDress();
        var dressResponse = createNewDressDto();

        when(mapper.performMapping(dressRequest, Dress.class)).thenReturn(dressToCreate);
        when(service.createOrUpdate(dressToCreate)).thenReturn(createdDress);
        when(mapper.performMapping(createdDress, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_APPLICATION + "/dresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressRequest)))
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
    void givenClient_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var dressRequest = createNewDressDto();
        var dressToCreate = createNewDress();
        var dressClient = createNewDress();
        var dressResponse = createNewDressDto();

        when(mapper.performMapping(dressRequest, Dress.class)).thenReturn(dressToCreate);
        when(service.createOrUpdate(dressToCreate)).thenReturn(dressClient);
        when(mapper.performMapping(dressClient, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/dresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressRequest)))
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
    void givenClient_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var dressRequest = BAD_BODY;
        var dressToCreate = createNewDress();
        var createdDress = createNewDress();
        var dressResponse = createNewDressDto();

        when(mapper.performMapping(dressRequest, Dress.class)).thenReturn(dressToCreate);
        when(service.createOrUpdate(dressToCreate)).thenReturn(createdDress);
        when(mapper.performMapping(createdDress, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_APPLICATION + "/dresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dressRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenClient_whenCallDelete_thenReturns_200_OK() throws Exception {
        var dressToDelete = createNewDress();
        var deletedDress = dressToDelete;
        var dressResponse = createNewDressDto();

        when(service.findById(ID)).thenReturn(dressToDelete);
        when(service.delete(dressToDelete.getId())).thenReturn(deletedDress);
        when(mapper.performMapping(deletedDress, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/dresses/{id}", ID)
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
    void givenClient_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var dressToDelete = createNewDress();
        var deletedClient = dressToDelete;
        var dressResponse = createNewDressDto();

        when(service.findById(ID)).thenReturn(dressToDelete);
        when(service.delete(dressToDelete.getId())).thenReturn(deletedClient);
        when(mapper.performMapping(deletedClient, DressDto.class)).thenReturn(dressResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_APPLICATION + "/dresses/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

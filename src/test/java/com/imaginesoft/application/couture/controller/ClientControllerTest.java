package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.service.ClientService;
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

@WebMvcTest(ClientController.class)
class ClientControllerTest extends BaseControllerTest {

    @MockBean
    private ClientService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var client = createNewClient();
        var clientResponse = createNewClientDto();

        when(service.findById(anyLong())).thenReturn(client);
        when(mapper.performMapping(client, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/clients/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewClient());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/clients/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewClient());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var client = createNewClient();
        var clients = createNewClients();
        var clientResponse = createNewClientDto();

        when(service.findAll()).thenReturn(clients);
        when(mapper.performMapping(client, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findAll()).thenReturn(createNewClients());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenClient_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var clientRequest = BAD_BODY;
        var clientToCreate = createNewClient();
        var createdClient = createNewClient();
        var clientResponse = createNewClientDto();

        when(mapper.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(service.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapper.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallCreate_thenReturns_200_OK() throws Exception {
        var clientRequest = createNewClientDto();
        var clientToCreate = createNewClient();
        var createdClient = createNewClient();
        var clientResponse = createNewClientDto();

        when(mapper.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(service.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapper.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("created")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenClient_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var clientRequest = createNewClientDto();
        var clientToCreate = createNewClient();
        var createdClient = createNewClient();
        var clientResponse = createNewClientDto();

        when(mapper.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(service.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapper.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("updated")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenClient_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var clientRequest = BAD_BODY;
        var clientToCreate = createNewClient();
        var createdClient = createNewClient();
        var clientResponse = createNewClientDto();

        when(mapper.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(service.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapper.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_200_OK() throws Exception {
        var clientToDelete = createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = createNewClientDto();

        when(service.findById(ID)).thenReturn(clientToDelete);
        when(service.delete(clientToDelete.getId())).thenReturn(deletedClient);
        when(mapper.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/clients/{id}", ID)
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
    void givenClient_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var clientToDelete = createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = createNewClientDto();

        when(service.findById(ID)).thenReturn(clientToDelete);
        when(service.delete(clientToDelete.getId())).thenReturn(deletedClient);
        when(mapper.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/clients/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var clientToDelete = createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = createNewClientDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(clientToDelete.getId())).thenReturn(deletedClient);
        when(mapper.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/clients/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}
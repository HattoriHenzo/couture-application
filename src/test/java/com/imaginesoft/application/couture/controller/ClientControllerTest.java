package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.service.ClientService;
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

@WebMvcTest(ClientController.class)
class ClientControllerTest extends BaseControllerTest {

    @MockBean
    private ClientService serviceMock;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var client = TestDataFactory.createNewClient();
        var clientDto = TestDataFactory.createNewClientDto();

        when(serviceMock.findById(anyLong())).thenReturn(client);
        when(mapperMock.performMapping(client, ClientDto.class)).thenReturn(clientDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

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
        when(serviceMock.findById(anyLong())).thenReturn(new Client());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/clients/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(serviceMock.findById(anyLong())).thenReturn(new Client());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var client = TestDataFactory.createNewClient();
        var clients = TestDataFactory.createNewClients();
        var clientDto = TestDataFactory.createNewClientDto();

        when(serviceMock.findAll()).thenReturn(clients);
        when(mapperMock.performMapping(client, ClientDto.class)).thenReturn(clientDto);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

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
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {
        when(serviceMock.findAll()).thenReturn(TestDataFactory.createNewClients());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenClient_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var clientRequest = BAD_BODY;
        var clientToCreate = TestDataFactory.createNewClient();
        var createdClient = TestDataFactory.createNewClient();
        var clientResponse = TestDataFactory.createNewClientDto();

        when(mapperMock.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(serviceMock.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapperMock.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallCreate_thenReturns_200_OK() throws Exception {
        var clientRequest = TestDataFactory.createNewClientDto();
        var clientToCreate = TestDataFactory.createNewClient();
        var createdClient = TestDataFactory.createNewClient();
        var clientResponse = TestDataFactory.createNewClientDto();

        when(mapperMock.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(serviceMock.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapperMock.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

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
        var clientRequest = TestDataFactory.createNewClientDto();
        var clientToCreate = TestDataFactory.createNewClient();
        var createdClient = TestDataFactory.createNewClient();
        var clientResponse = TestDataFactory.createNewClientDto();

        when(mapperMock.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(serviceMock.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapperMock.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

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
        var clientToCreate = TestDataFactory.createNewClient();
        var createdClient = TestDataFactory.createNewClient();
        var clientResponse = TestDataFactory.createNewClientDto();

        when(mapperMock.performMapping(clientRequest, Client.class)).thenReturn(clientToCreate);
        when(serviceMock.createOrUpdate(clientToCreate)).thenReturn(createdClient);
        when(mapperMock.performMapping(createdClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_200_OK() throws Exception {
        var clientToDelete = TestDataFactory.createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = TestDataFactory.createNewClientDto();

        when(serviceMock.findById(ID)).thenReturn(clientToDelete);
        when(serviceMock.delete(clientToDelete)).thenReturn(deletedClient);
        when(mapperMock.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

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
        var clientToDelete = TestDataFactory.createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = TestDataFactory.createNewClientDto();

        when(serviceMock.findById(ID)).thenReturn(clientToDelete);
        when(serviceMock.delete(clientToDelete)).thenReturn(deletedClient);
        when(mapperMock.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/clients/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {

        var clientToDelete = TestDataFactory.createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = TestDataFactory.createNewClientDto();

        when(serviceMock.findById(ID)).thenReturn(null);
        when(serviceMock.delete(clientToDelete)).thenReturn(deletedClient);
        when(mapperMock.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeMock.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/clients/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }

}
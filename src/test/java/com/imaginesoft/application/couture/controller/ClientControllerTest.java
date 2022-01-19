package com.imaginesoft.application.couture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.service.ClientService;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import com.imaginesoft.application.couture.util.TestDataFactory;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest implements WithAssertions {

    private static final Long ID = 1L;
    private static final Long BAD_ID = 5L;
    private static final String BAD_URI = "/bad/uri";
    private static final String BAD_PATH_PARAM = "/param\\";
    private static final String BAD_BODY = "bad body";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService serviceMock;

    @MockBean
    private MapperWrapper mapperMock;

    @MockBean
    private DateTimeWrapper dateTimeWrapperMock;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallGetId_thenReturns_200_OK() throws Exception {

        var client = TestDataFactory.createNewClient();
        var clientDto = TestDataFactory.createNewClientDto();

        when(serviceMock.getById(anyLong())).thenReturn(client);
        when(mapperMock.performMapping(client, ClientDto.class)).thenReturn(clientDto);
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(get("/api/clients/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is("2022-01-04")))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallGetId_thenReturns_400_BAD_REQUEST() throws Exception {

        when(serviceMock.getById(anyLong())).thenReturn(new Client());

        mockMvc.perform(get("/api/clients/{id}", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallGetId_thenReturns_404_NOT_FOUND() throws Exception {

        when(serviceMock.getById(anyLong())).thenReturn(new Client());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallGetAll_thenReturns_200_OK() throws Exception {

        var client = TestDataFactory.createNewClient();
        var clients = TestDataFactory.createNewClients();
        var clientDto = TestDataFactory.createNewClientDto();

        when(serviceMock.getAll()).thenReturn(clients);
        when(mapperMock.performMapping(client, ClientDto.class)).thenReturn(clientDto);
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(get("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is("2022-01-04")))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenAll_whenCallGetAll_thenReturns_404_BAD_REQUEST() throws Exception {

        when(serviceMock.getAll()).thenReturn(TestDataFactory.createNewClients());

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
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(post("/api/client")
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
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(post("/api/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is("2022-01-04")))
                .andExpect(jsonPath("$.message", containsString("created")))
                .andExpect(jsonPath("$.data", hasSize(1)));
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
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(put("/api/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is("2022-01-04")))
                .andExpect(jsonPath("$.message", containsString("updated")))
                .andExpect(jsonPath("$.data", hasSize(1)));
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
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(put("/api/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_200_OK() throws Exception {

        var clientToDelete = TestDataFactory.createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = TestDataFactory.createNewClientDto();

        when(serviceMock.getById(ID)).thenReturn(clientToDelete);
        when(serviceMock.delete(clientToDelete)).thenReturn(deletedClient);
        when(mapperMock.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(delete("/api/clients/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is("2022-01-04")))
                .andExpect(jsonPath("$.message", containsString("deleted")))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {

        var clientToDelete = TestDataFactory.createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = TestDataFactory.createNewClientDto();

        when(serviceMock.getById(ID)).thenReturn(clientToDelete);
        when(serviceMock.delete(clientToDelete)).thenReturn(deletedClient);
        when(mapperMock.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(delete("/api/clients/{id}", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenClient_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {

        var clientToDelete = TestDataFactory.createNewClient();
        var deletedClient = clientToDelete;
        var clientResponse = TestDataFactory.createNewClientDto();

        when(serviceMock.getById(ID)).thenReturn(null);
        when(serviceMock.delete(clientToDelete)).thenReturn(deletedClient);
        when(mapperMock.performMapping(deletedClient, ClientDto.class)).thenReturn(clientResponse);
        when(dateTimeWrapperMock.getCurrentDateTime(any(Clock.class))).thenReturn("2022-01-04");

        mockMvc.perform(delete("/api/clients/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.date", is("2022-01-04")))
                .andExpect(result -> assertThat(result.getResolvedException().getMessage()).contains("not found"));
    }

}
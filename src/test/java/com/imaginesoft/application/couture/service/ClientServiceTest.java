package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.repository.ClientRepository;
import com.imaginesoft.application.couture.service.exception.DomainRulesException;
import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.imaginesoft.application.couture.util.TestDataFactory.createNewClients;
import static com.imaginesoft.application.couture.util.TestDataFactory.createNewClient;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest implements WithAssertions {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenClients_whenGettingClients_thenGetAllClients() throws RecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewClients());
        assertThat(service.getAll().size()).isEqualTo(createNewClients().size());
    }

    @Test
    void givenClient_whenCreateClient_thenClientIsCreated() {
        Client client = createNewClient();
        when(repository.save(client)).thenReturn(client);

        assertThat(service.createOrUpdate(client)).isNotNull();
    }

    @Test
    void givenClient_whenFirstNameIsEmpty_thenClientThrowException() {

        Client newClient = createNewClient();
        newClient.setFirstName("");

        Exception exception = assertThrows(DomainRulesException.class,
                () -> service.createOrUpdate(newClient));

        assertThat(exception.getMessage()).contains("First Name");
    }

    @Test
    void givenClient_whenLastNameIsEmpty_thenClientThrowException() {

        Client newClient = createNewClient();
        newClient.setLastName("");

        Exception exception = assertThrows(DomainRulesException.class,
                () -> service.createOrUpdate(newClient));

        assertThat(exception.getMessage()).contains("Last Name");
    }

    @Test
    void givenClient_whenTelephoneIsEmpty_thenClientThrowException() {

        Client newClient = createNewClient();
        newClient.setTelephone("");

        Exception exception = assertThrows(DomainRulesException.class,
                () -> service.createOrUpdate(newClient));

        assertThat(exception.getMessage()).contains("Telephone");
    }

    @Test
    void givenClient_whenUpdateClient_thenClientIsUpdated() {

        Client client = createNewClient();
        client.setFirstName("POLO");
        when(repository.save(client)).thenReturn(client);
        when(repository.getById(anyLong())).thenReturn(client);

        assertAll(
                () -> assertThat(service.createOrUpdate(client)).isNotNull(),
                () -> {
                    Client updatedClient = service.getById(1L);
                    assertAll(
                            () -> assertThat(updatedClient.getFirstName()).isEqualTo(client.getFirstName()),
                            () -> assertThat(updatedClient.getLastName()).isEqualTo(client.getLastName()),
                            () -> assertThat(updatedClient.getTelephone()).isEqualTo(client.getTelephone()),
                            () -> assertThat(updatedClient.getGender()).isEqualTo(client.getGender())
                    );
                }
        );
    }

    @Test
    void givenClient_whenUpdateClient_thenClientIsDeleted() {

        Client deletedClient = createNewClient();
        when(repository.getById(anyLong())).thenReturn(deletedClient);

        assertThat(service.delete(deletedClient)).isNotNull();
    }

}
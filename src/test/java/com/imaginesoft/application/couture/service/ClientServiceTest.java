package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.repository.ClientRepository;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import com.imaginesoft.application.couture.util.TestDataFactory;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.CLIENT_ID;
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
    private ClientService underTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenClients_whenGettingClients_thenGetAllClients() throws RecordNotFoundException {

        when(repository.findAll()).thenReturn(TestDataFactory.createNewClients());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenClient_whenCreateClient_thenClientIsCreated() {

        var newClient = createNewClient();
        when(repository.save(newClient)).thenReturn(newClient);

        var createdClient = underTest.createOrUpdate(newClient);

        assertThat(createdClient).isNotNull();
    }

    @Test
    void givenClient_whenFirstNameIsEmpty_thenClientThrowException() {

        var newClient = createNewClient();
        newClient.setFirstName("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newClient));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenClient_whenLastNameIsEmpty_thenClientThrowException() {

        var newClient = createNewClient();
        newClient.setLastName("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newClient));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenClient_whenTelephoneIsEmpty_thenClientThrowException() {

        var newClient = createNewClient();
        newClient.setTelephone("");
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newClient));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenClient_whenUpdateClient_thenClientIsUpdated() {

        var client = createNewClient();
        client.setFirstName("POLO");
        when(repository.save(client)).thenReturn(client);
        when(repository.findById(anyLong())).thenReturn(Optional.of(client));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(client)).isNotNull(),
                () -> {
                    Client updatedClient = underTest.findById(CLIENT_ID);
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
    void givenClient_whenDeleteClient_thenClientIsDeleted() {

        var deletedClient = createNewClient();
        when(repository.findById(anyLong())).thenReturn(Optional.of(deletedClient));

        assertThat(underTest.delete(deletedClient)).isNotNull();
    }

}
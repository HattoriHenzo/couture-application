package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.model.Gender;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("h2-test")
class ClientRepositoryTest implements WithAssertions {

    @Autowired
    private ClientRepository repository;

    @Test
    void givenClients_whenGettingClients_thenGetAllClients() {

        int numberOfClients = repository.findAll().size();
        assertThat(numberOfClients).isEqualTo(NUMBER_OF_CLIENTS);
    }

    @Test
    void givenClient_whenCreateClient_thenClientExists() {

        Client newClient = createNewClient();
        // Set the id of the new client
        newClient.setId(6L);
        repository.save(newClient);
        Client addedClient = repository.getById(CLIENT_ADDED_ID);

        assertAll(
                () -> assertThat(addedClient).isNotNull(),
                () -> assertThat(addedClient.getFirstName()).isEqualTo(FIRST_NAME),
                () -> assertThat(addedClient.getLastName()).isEqualTo(LAST_NAME),
                () -> assertThat(addedClient.getGender()).isEqualTo(Gender.MALE),
                () -> assertThat(addedClient.getTelephone()).isEqualTo(TELEPHONE)
        );
    }

    @Test
    void givenClient_whenUpdateClient_thenClientHasChanged() {

        Client clientToUpdate = repository.getById(CLIENT_ID);
        clientToUpdate.setFirstName(EDITED_FIRST_NAME);
        clientToUpdate.setTelephone(EDITED_TELEPHONE);

        Client updatedClient = repository.save(clientToUpdate);

        assertAll(
                () -> assertThat(updatedClient.getId()).isEqualTo(CLIENT_ID),
                () -> assertThat(updatedClient.getFirstName()).isEqualTo(EDITED_FIRST_NAME),
                () -> assertThat(updatedClient.getLastName()).isEqualTo(LAST_NAME),
                () -> assertThat(updatedClient.getGender()).isEqualTo(Gender.MALE),
                () -> assertThat(updatedClient.getTelephone()).isEqualTo(EDITED_TELEPHONE)
        );
    }

    @Test
    void givenClient_whenDeleteClient_thenClientDoesNotExists() {

        Client clientToDelete = repository.getById(CLIENT_DELETED_ID);
        repository.delete(clientToDelete);
        Optional<Client> deletedClient = repository.findById(CLIENT_DELETED_ID);

        assertThat(deletedClient).isNotPresent();
    }

}
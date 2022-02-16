package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Gender;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class ClientRepositoryTest implements WithAssertions {

    @Autowired
    private ClientRepository repository;

    @Test
    void givenClients_whenGettingClients_thenGetAllClients() {

        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenClient_whenCreateClient_thenClientExists() {

        var newClient = createNewClient();
        var createdClient = repository.save(newClient);

        assertAll(
                () -> assertThat(createdClient).isNotNull(),
                () -> assertThat(createdClient.getFirstName()).isEqualTo(CLIENT_FIRST_NAME),
                () -> assertThat(createdClient.getLastName()).isEqualTo(CLIENT_LAST_NAME),
                () -> assertThat(createdClient.getGender()).isEqualTo(Gender.MALE),
                () -> assertThat(createdClient.getTelephone()).isEqualTo(CLIENT_TELEPHONE)
        );
    }

    @Test
    void givenClient_whenUpdateClient_thenClientHasChanged() {

        var clientToUpdate = repository.getById(CLIENT_ID);
        clientToUpdate.setFirstName(CLIENT_EDITED_FIRST_NAME);
        clientToUpdate.setTelephone(CLIENT_EDITED_TELEPHONE);

        var updatedClient = repository.save(clientToUpdate);

        assertAll(
                () -> assertThat(updatedClient.getId()).isEqualTo(CLIENT_ID),
                () -> assertThat(updatedClient.getFirstName()).isEqualTo(CLIENT_EDITED_FIRST_NAME),
                () -> assertThat(updatedClient.getLastName()).isEqualTo(CLIENT_LAST_NAME),
                () -> assertThat(updatedClient.getGender()).isEqualTo(Gender.MALE),
                () -> assertThat(updatedClient.getTelephone()).isEqualTo(CLIENT_EDITED_TELEPHONE)
        );
    }

    @Test
    void givenClient_whenDeleteClient_thenClientDoesNotExists() {

        var clientToDelete = repository.findById(CLIENT_TO_DELETE);
        repository.delete(clientToDelete.get());
        var deletedClient = repository.findById(CLIENT_TO_DELETE);

        assertThat(deletedClient).isNotPresent();
    }

}
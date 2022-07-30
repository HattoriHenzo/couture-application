package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.LoginDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> assertThat(Objects.requireNonNull(result.getResponseBody()).getData()).isNotEmpty());
    }

    @Test
    void integrationTest_For_FindById() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/logins/{ID}", LOGIN_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var logins = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<LoginDto>>() {
                            });
                    var foundLogin = logins.get(0);
                    assertAll(
                            () -> assertThat(LOGIN_ID).isEqualTo(foundLogin.getId()),
                            () -> assertThat(LOGIN_USERNAME).isEqualTo(foundLogin.getUsername()),
                            () -> assertThat(LOGIN_PASSWORD).isEqualTo(foundLogin.getPassword()),
                            () -> assertThat(LOGIN_CATEGORY.name()).isEqualTo(foundLogin.getLoginCategory())
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newLogin = createNewLoginDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newLogin)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var logins = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<LoginDto>>() {
                            });
                    var createdLogin = logins.get(0);
                    assertAll(
                            () -> assertThat(newLogin.getId()).isEqualTo(createdLogin.getId()),
                            () -> assertThat(newLogin.getUsername()).isEqualTo(createdLogin.getUsername()),
                            () -> assertThat(newLogin.getPassword()).isEqualTo(createdLogin.getPassword()),
                            () -> assertThat(newLogin.getLoginCategory()).isEqualTo(createdLogin.getLoginCategory())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var loginToUpdate = createNewLoginDto();
        loginToUpdate.setUsername(LOGIN_EDITED_USERNAME);
        loginToUpdate.setPassword(LOGIN_EDITED_PASSWORD);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(loginToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var logins = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<LoginDto>>() {
                            });
                    var updatedLogin = logins.get(0);
                    assertAll(
                            () -> assertThat(loginToUpdate.getId()).isEqualTo(updatedLogin.getId()),
                            () -> assertThat(loginToUpdate.getUsername()).isEqualTo(updatedLogin.getUsername()),
                            () -> assertThat(loginToUpdate.getPassword()).isEqualTo(updatedLogin.getPassword()),
                            () -> assertThat(loginToUpdate.getLoginCategory()).isEqualTo(updatedLogin.getLoginCategory())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1_ADMIN + "/logins/{ID}", LOGIN_TO_DELETE)
                .exchange()
                .expectStatus().isOk();
    }
}

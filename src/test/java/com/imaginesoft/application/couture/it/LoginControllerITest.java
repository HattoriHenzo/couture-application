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
                .uri(ApplicationDataFactory.API_V1 + "/logins")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    assertThat(Objects.requireNonNull(result.getResponseBody()).getData()).isNotEmpty();
                });
    }

    @Test
    void integrationTest_For_FindById() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/logins/{ID}", LOGIN_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var logins = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<LoginDto>>() {
                            });
                    var foundLogin = logins.get(0);
                    assertAll(
                            () -> assertThat(foundLogin.getId()).isEqualTo(LOGIN_ID),
                            () -> assertThat(foundLogin.getUsername()).isEqualTo(LOGIN_USERNAME),
                            () -> assertThat(foundLogin.getPassword()).isEqualTo(LOGIN_PASSWORD),
                            () -> assertThat(foundLogin.getLoginCategory()).isEqualTo(LOGIN_CATEGORY.name())
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newLogin = createNewLoginDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/logins")
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
                            () -> assertThat(createdLogin.getId()).isEqualTo(newLogin.getId()),
                            () -> assertThat(createdLogin.getUsername()).isEqualTo(newLogin.getUsername()),
                            () -> assertThat(createdLogin.getPassword()).isEqualTo(newLogin.getPassword()),
                            () -> assertThat(createdLogin.getLoginCategory()).isEqualTo(newLogin.getLoginCategory())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var loginToUpdate = createNewLoginDto();
        loginToUpdate.setUsername(LOGIN_EDITED_USERNAME);
        loginToUpdate.setPassword(LOGIN_EDITED_PASSWORD);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/logins")
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
                            () -> assertThat(updatedLogin.getId()).isEqualTo(loginToUpdate.getId()),
                            () -> assertThat(updatedLogin.getUsername()).isEqualTo(loginToUpdate.getUsername()),
                            () -> assertThat(updatedLogin.getPassword()).isEqualTo(loginToUpdate.getPassword()),
                            () -> assertThat(updatedLogin.getLoginCategory()).isEqualTo(loginToUpdate.getLoginCategory())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/logins/{ID}", ID)
                .exchange()
                .expectStatus().isOk();
    }
}

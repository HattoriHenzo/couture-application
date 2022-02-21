package com.imaginesoft.application.couture.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class LoginRepositoryTest {

    @Autowired
    private LoginRepository repository;

    @Test
    void givenLogin_whenCreateLogin_thenLoginExists() {

        var newLogin = createNewLogin();
        var createdLogin = repository.save(newLogin);

        assertAll(
                () -> assertThat(createdLogin).isNotNull(),
                () -> assertThat(createdLogin.getUsername()).isEqualTo(LOGIN_USERNAME),
                () -> assertThat(createdLogin.getPassword()).isEqualTo(LOGIN_PASSWORD),
                () -> assertThat(createdLogin.getLoginCategory()).isEqualTo(LOGIN_CATEGORY)
        );
    }

    @Test
    void givenLogin_whenUpdateLogin_thenLoginHasChanged() {

        var newLogin = createNewLogin();

        var loginToUpdate = repository.save(newLogin);
        loginToUpdate.setUsername(LOGIN_USERNAME);
        loginToUpdate.setPassword(LOGIN_PASSWORD);

        var updatedLogin = repository.save(loginToUpdate);

        assertAll(
                () -> assertThat(updatedLogin.getId()).isEqualTo(loginToUpdate.getId()),
                () -> assertThat(updatedLogin.getUsername()).isEqualTo(LOGIN_USERNAME),
                () -> assertThat(updatedLogin.getPassword()).isEqualTo(LOGIN_PASSWORD)
        );
    }

    @Test
    void givenLogin_whenDeleteLogin_thenLoginDoesNotExists() {

        var loginToDelete = repository.findById(LOGIN_ID);
        repository.delete(loginToDelete.get());
        var deletedLogin = repository.findById(LOGIN_ID);

        assertThat(deletedLogin).isNotPresent();
    }
}
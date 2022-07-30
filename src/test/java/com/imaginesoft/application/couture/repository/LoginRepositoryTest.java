package com.imaginesoft.application.couture.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DataJpaTest
@ActiveProfiles("test")
class LoginRepositoryTest {

    @Autowired
    private LoginRepository repository;

    @Test
    void givenLogins_whenGettingLogins_thenGetAllLogins() {
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void givenLogin_whenGettingLoginById_thenGetLogin() {
        var foundLogin = repository.findById(LOGIN_ID);
        assumingThat(foundLogin.isPresent(), () -> foundLogin.ifPresent(
                value -> assertAll(
                        () -> assertThat(value.getId()).isEqualTo(LOGIN_ID),
                        () -> assertThat(value.getUsername()).isEqualTo(LOGIN_USERNAME),
                        () -> assertThat(value.getPassword()).isEqualTo(LOGIN_PASSWORD),
                        () -> assertThat(value.getLoginCategory()).isEqualTo(LOGIN_CATEGORY),
                        () -> assertThat(value.isCredentialsNonExpired()).isTrue(),
                        () -> assertThat(value.isAccountNonExpired()).isTrue(),
                        () -> assertThat(value.isAccountNonLocked()).isTrue(),
                        () -> assertThat(value.isEnabled()).isTrue()
                )
        ));
    }

    @Test
    void givenLogin_whenCreateLogin_thenLoginExists() {
        var newLogin = createNewLogin();
        var createdLogin = repository.save(newLogin);

        assertAll(
                () -> assertThat(createdLogin).isNotNull(),
                () -> assertThat(createdLogin.getUsername()).isEqualTo(LOGIN_USERNAME),
                () -> assertThat(createdLogin.getPassword()).isEqualTo(LOGIN_PASSWORD),
                () -> assertThat(createdLogin.getLoginCategory()).isEqualTo(LOGIN_CATEGORY),
                () -> assertThat(createdLogin.isCredentialsNonExpired()).isTrue(),
                () -> assertThat(createdLogin.isAccountNonExpired()).isTrue(),
                () -> assertThat(createdLogin.isAccountNonLocked()).isTrue(),
                () -> assertThat(createdLogin.isEnabled()).isTrue()
        );
    }

    @Test
    void givenLogin_whenUpdateLogin_thenLoginHasChanged() {
        var loginToUpdate = repository.findById(LOGIN_ID);
        assumingThat(loginToUpdate.isPresent(), () -> loginToUpdate.ifPresent(
                value -> {
                    value.setUsername(LOGIN_USERNAME);
                    value.setPassword(LOGIN_PASSWORD);
                    var updatedLogin = repository.save(value);
                    assertAll(
                            () -> assertThat(updatedLogin.getId()).isEqualTo(loginToUpdate.get().getId()),
                            () -> assertThat(updatedLogin.getUsername()).isEqualTo(loginToUpdate.get().getUsername()),
                            () -> assertThat(updatedLogin.getPassword()).isEqualTo(loginToUpdate.get().getPassword()),
                            () -> assertThat(updatedLogin.isCredentialsNonExpired()).isTrue(),
                            () -> assertThat(updatedLogin.isAccountNonExpired()).isTrue(),
                            () -> assertThat(updatedLogin.isAccountNonLocked()).isTrue(),
                            () -> assertThat(updatedLogin.isEnabled()).isTrue()
                    );
                }
        ));
    }

    @Test
    void givenLogin_whenDeleteLogin_thenLoginDoesNotExists() {
        var loginToDelete = repository.findById(LOGIN_ID);
        assumingThat(loginToDelete.isPresent(), () -> loginToDelete.ifPresent(
                value -> {
                    repository.delete(value);
                    var deletedLogin = repository.findById(LOGIN_ID);
                    assertThat(deletedLogin).isNotPresent();
                }
        ));
    }
}
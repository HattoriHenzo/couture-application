package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.LoginCategory;
import com.imaginesoft.application.couture.repository.LoginRepository;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest implements WithAssertions {

    @Mock
    private LoginRepository repository;

    @InjectMocks
    private LoginService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenLogin_whenFindingLogin_thenFindAllLogins() throws RecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewLogins());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenLogin_whenCreateLogin_thenLoginIsCreated() {
        var newLogin = createNewLogin();
        when(repository.save(newLogin)).thenReturn(newLogin);
        var createdEmployee = underTest.createOrUpdate(newLogin);

        assertThat(createdEmployee).isNotNull();
    }

    @Test
    void givenLogin_whenUserNameIsEmpty_thenLoginThrowException() {
        var newLogin = createNewLogin();
        newLogin.setUsername(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newLogin));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenLogin_whenPasswordIsEmpty_thenLoginThrowException() {
        var newLogin = createNewLogin();
        newLogin.setPassword(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newLogin));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenLogin_whenUpdateLogin_thenLoginIsUpdated() {
        var login = createNewLogin();
        login.setUsername("POLO");
        when(repository.save(login)).thenReturn(login);
        when(repository.findById(anyLong())).thenReturn(Optional.of(login));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(login)).isNotNull(),
                () -> {
                    var updatedLogin = underTest.findById(LOGIN_ID);
                    assertAll(
                            () -> assertThat(updatedLogin.getUsername()).isEqualTo(login.getUsername()),
                            () -> assertThat(updatedLogin.getPassword()).isEqualTo(login.getPassword())
                    );
                }
        );
    }

    @Test
    void givenLogin_whenDeleteLogin_thenLoginIsDeleted() {
        var deletedLogin = createNewLogin();
        when(repository.findById(anyLong())).thenReturn(Optional.of(deletedLogin));

        assertThat(underTest.delete(deletedLogin)).isNotNull();
    }
}

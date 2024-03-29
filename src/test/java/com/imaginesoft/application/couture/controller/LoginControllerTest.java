package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.dto.LoginDto;
import com.imaginesoft.application.couture.service.LoginService;
import com.imaginesoft.application.couture.service.exception.DomainRecordNotFoundException;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.Clock;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest extends BaseControllerTest {

    @BeforeEach
    void setUp() {

    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var login = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(service.findById(login.getId())).thenReturn(login);
        when(mapper.performMapping(login, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_ADMIN + "/logins/{id}", LOGIN_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallLoadUserByUsername_thenReturns_200_OK() throws Exception {
        var login = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(service.loadUserByUsername(login.getUsername())).thenReturn(login);
        when(mapper.performMapping(login, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_ADMIN + "/logins/{id}", LOGIN_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.loadUserByUsername(anyString())).thenReturn(new Login());

        mockMvc.perform(get(BAD_URI, LOGIN_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var login = createNewLogin();
        var logins = createNewLogins();
        var loginResponse = createNewLoginDto();

        when(service.findAll()).thenReturn(logins);
        when(mapper.performMapping(login, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(greaterThan(0)))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {
        when(service.findAll()).thenReturn(createNewLogins());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenLogin_whenCallCreate_thenReturns_200_OK() throws Exception {
        var loginRequest = createNewLoginDto();
        var loginToCreate = createNewLogin();
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("created")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenLogin_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var loginRequest = createNewLoginDto();
        var loginToCreate = createNewLogin();
        loginToCreate.setUsername("");
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("updated")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenLogin_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var loginRequest = BAD_BODY;
        var loginToCreate = createNewLogin();
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1_ADMIN + "/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenLogin_whenCallDelete_thenReturns_200_OK() throws Exception {
        var loginToDelete = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(service.findById(LOGIN_ID)).thenReturn(loginToDelete);
        when(service.delete(LOGIN_ID)).thenReturn(loginToDelete);
        when(mapper.performMapping(loginToDelete, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_ADMIN + "/logins/{id}", LOGIN_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("deleted")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @WithMockUser(username = "spring", roles = {"ADMIN"})
    @Test
    void givenLogin_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var loginToDelete = createNewLogin();

        when(service.delete(anyLong())).thenThrow(DomainRecordNotFoundException.class);
        when(mapper.performMapping(loginToDelete, LoginDto.class)).thenReturn(null);
        when(dateTime.getCurrentDateTime(ArgumentMatchers.any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1_ADMIN + "/logins/{id}", anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

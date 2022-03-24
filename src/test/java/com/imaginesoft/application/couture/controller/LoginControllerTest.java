package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.LoginDto;
import com.imaginesoft.application.couture.model.Login;
import com.imaginesoft.application.couture.service.LoginService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.Clock;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest extends BaseControllerTest {

    @MockBean
    private LoginService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var login = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(service.findById(anyLong())).thenReturn(login);
        when(mapper.performMapping(login, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/logins/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(new Login());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/logins/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(new Login());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var login = createNewLogin();
        var logins = createNewLogins();
        var loginResponse = createNewLoginDto();

        when(service.findAll()).thenReturn(logins);
        when(mapper.performMapping(login, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/logins")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(greaterThan(0)))
                );
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {
        when(service.findAll()).thenReturn(createNewLogins());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenLogin_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var loginRequest = BAD_BODY;
        var loginToCreate = createNewLogin();
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenLogin_whenCallCreate_thenReturns_200_OK() throws Exception {
        var loginRequest = createNewLoginDto();
        var loginToCreate = createNewLogin();
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/logins")
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

    @Test
    void givenLogin_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var loginRequest = createNewLoginDto();
        var loginToCreate = createNewLogin();
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/logins")
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

    @Test
    void givenLogin_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var loginRequest = BAD_BODY;
        var loginToCreate = createNewLogin();
        var createdLogin = createNewLogin();
        var loginResponse = createNewLoginDto();

        when(mapper.performMapping(loginRequest, Login.class)).thenReturn(loginToCreate);
        when(service.createOrUpdate(loginToCreate)).thenReturn(createdLogin);
        when(mapper.performMapping(createdLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenLogin_whenCallDelete_thenReturns_200_OK() throws Exception {
        var loginToDelete = createNewLogin();
        var deletedLogin = loginToDelete;
        var loginResponse = createNewLoginDto();

        when(service.findById(ID)).thenReturn(loginToDelete);
        when(service.delete(loginToDelete.getId())).thenReturn(deletedLogin);
        when(mapper.performMapping(deletedLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/logins/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("deleted")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenLogin_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var loginToDelete = createNewLogin();
        var deletedLogin = loginToDelete;
        var loginResponse = createNewLoginDto();

        when(service.findById(ID)).thenReturn(loginToDelete);
        when(service.delete(loginToDelete.getId())).thenReturn(deletedLogin);
        when(mapper.performMapping(deletedLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/logins/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenLogin_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var loginToDelete = createNewLogin();
        var deletedLogin = loginToDelete;
        var loginResponse = createNewLoginDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(loginToDelete.getId())).thenReturn(deletedLogin);
        when(mapper.performMapping(deletedLogin, LoginDto.class)).thenReturn(loginResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/logins/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}

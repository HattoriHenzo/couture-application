package com.imaginesoft.application.couture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginesoft.application.couture.configuration.ApplicationConfig;
import com.imaginesoft.application.couture.configuration.security.ApplicationSecurityConfig;
import com.imaginesoft.application.couture.configuration.security.service.LoginService;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import(value = {ApplicationSecurityConfig.class, ApplicationConfig.class})
public abstract class BaseControllerTest implements WithAssertions {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MapperWrapper mapper;

    @MockBean
    protected DateTimeWrapper dateTime;

    @MockBean
    protected LoginService service;

}

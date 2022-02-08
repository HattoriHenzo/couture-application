package com.imaginesoft.application.couture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginesoft.application.couture.service.DressTypeService;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public abstract class BaseControllerTest implements WithAssertions {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MapperWrapper mapperMock;

    @MockBean
    protected DateTimeWrapper dateTimeMock;

}

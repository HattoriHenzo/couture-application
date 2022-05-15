package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Testcontainers
@DirtiesContext
@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest implements WithAssertions {

    @Autowired
    protected WebApplicationContext context;

    @LocalServerPort
    private String LOCAL_PORT;

    private static final String VERSION = "mariadb:10.6.5";

    @Container
    protected static MariaDBContainer<?> CONTAINER = new MariaDBContainer<>(VERSION);

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        var BASE_URL = String.format("http://localhost:%s", LOCAL_PORT);
        webTestClient = MockMvcWebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl(BASE_URL)
                .build();
    }
}

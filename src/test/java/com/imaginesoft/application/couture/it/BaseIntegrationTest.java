package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest implements WithAssertions {

    private static final String VERSION = "mariadb:10.6.5";

    @Container
    protected static MariaDBContainer<?> CONTAINER = new MariaDBContainer<>(VERSION);

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected WebTestClient webTestClient;
}

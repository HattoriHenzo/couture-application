package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
@Sql(scripts = {"/sql/mariadb/schema.sql", "/sql/mariadb/data.sql"})
public abstract class BaseIntegrationTest implements WithAssertions {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected WebTestClient webTestClient;

}

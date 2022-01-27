package com.imaginesoft.application.couture.it;

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
public abstract class BaseIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

}

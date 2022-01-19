package com.imaginesoft.application.couture.it;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mariabdb-test")
public class BaseIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

}

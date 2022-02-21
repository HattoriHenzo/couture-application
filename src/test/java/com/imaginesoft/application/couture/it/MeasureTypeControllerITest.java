package com.imaginesoft.application.couture.it;

import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;

class MeasureTypeControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {

        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/measure-types")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    assertThat(result.getResponseBody().getData()).isNotEmpty();
                });
    }
}

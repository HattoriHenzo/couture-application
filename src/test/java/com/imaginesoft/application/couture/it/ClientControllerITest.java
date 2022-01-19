package com.imaginesoft.application.couture.it;

import com.imaginesoft.application.couture.dto.ClientDto;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;


public class ClientControllerITest extends BaseIntegrationTest implements WithAssertions {

    @Test
    public void integrationTest_forGetClient() {

        webTestClient.get()
                .uri("/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ClientDto.class).hasSize(0);
    }
}

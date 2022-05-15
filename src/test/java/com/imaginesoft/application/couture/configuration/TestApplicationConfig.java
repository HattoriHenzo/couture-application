package com.imaginesoft.application.couture.configuration;

import com.imaginesoft.application.couture.configuration.security.ApplicationSecurityConfig;
import com.imaginesoft.application.couture.configuration.security.service.LoginService;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import com.imaginesoft.application.couture.util.ModelMapperWrapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestApplicationConfig {

    @Bean
    public DateTimeWrapper createDateTimeWrapper() {
        return new DateTimeWrapper();
    }

    @Bean
    public MapperWrapper createModelMapperWrapper() {
        return new ModelMapperWrapper();
    }
}

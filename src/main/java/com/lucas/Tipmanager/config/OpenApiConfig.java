package com.lucas.Tipmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tipManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tip Manager API")
                        .version("1.0")
                        .description(
                                "API for managing restaurant employees, " +
                                        "work days and tip distribution."
                        )
                );
    }
}
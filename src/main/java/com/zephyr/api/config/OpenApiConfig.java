package com.zephyr.api.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("Zephyr Weather API")
                        .version("1.0")
                        .description("REST API for weather data management")
                        .contact(new Contact()
                                .name("Pamella Binotto")
                                .email("pamellabinotto@gmail.com")
                        )
                )

                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                )

                .schemaRequirement(
                        "bearerAuth",
                        new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }
}
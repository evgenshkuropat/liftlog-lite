package com.example.liftloglite.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI liftLogOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LiftLog Lite API")
                        .description("Minimal strength training tracker (Spring Boot). Educational project for REST API, validation, and clean architecture.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Evžen Shkuropat")
                                .url("https://github.com/evgenshkuropat")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local server")
                        // позже можно добавить: new Server().url("https://your-domain.com").description("Production")
                ));
    }
}
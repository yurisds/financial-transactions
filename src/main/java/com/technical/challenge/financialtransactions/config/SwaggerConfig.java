package com.technical.challenge.financialtransactions.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.url}")
    private String swaggerUrl;

    @Bean
    public OpenAPI defineOpenApi() {
        var server = new Server();
        server.setUrl(swaggerUrl);

        var information = new Info()
                .title("API de soluções financeiras")
                .version("1.0");
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
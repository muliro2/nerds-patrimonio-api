package com.nerds.patrimonio.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Controle de Patrimônio - NERDS")
                .description("API REST para gerenciamento de equipamentos e ativos alocados no projeto de extensão NERDS-UFC.")
                .contact(new Contact()
                    .name("Sérgio Murilo de Lima Pascoal")
                    .email("sergiopascoal9@gmail.com"))
                .version("1.0.0"));
    }
}
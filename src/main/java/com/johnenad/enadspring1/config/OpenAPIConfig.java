package com.johnenad.enadspring1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${john-demo.server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(serverUrl);
        server.setDescription("Server URL");

        Contact contact = new Contact();
        contact.setEmail("johndemo@johndemo1234.com");
        contact.setName("John Demo");
        contact.setUrl("https://www.johndemo1234.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Person API")
                .version("1.0")
                .contact(contact)
                .description("Persons API").termsOfService("https://www.johndemo1234.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
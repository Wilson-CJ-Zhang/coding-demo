package pers.wilson.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shopping Demo")
                        .description("Chengjie's Demo for UBS coding test")
                        .version("0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("API Demo")
                        .url("/"));
    }
}

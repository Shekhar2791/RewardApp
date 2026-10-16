package com.cg.reward.calculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for Swagger/OpenAPI documentation.
 * Exposes API documentation at /swagger-ui/index.html.
 * @author SKH
 */

@Configuration
public class OpenAPIConfig {

    /**
     * Creates and configures OpenAPI documentation metadata.
     *
     * @return OpenAPI documentation details
     */
	
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Rewards API").version("1.0").description("Customer reward calculation API"));
    }
}

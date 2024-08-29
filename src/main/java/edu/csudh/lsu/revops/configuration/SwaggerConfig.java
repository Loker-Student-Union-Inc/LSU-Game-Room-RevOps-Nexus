package edu.csudh.lsu.revops.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * SwaggerConfig class is responsible for configuring OpenAPI (Swagger) for API documentation.
 * </p>
 *
 * <p>
 * This class sets up OpenAPI to document all REST APIs available in the application.
 * </p>
 *
 * <p>
 * Created by: Digvijay Hethur Jagadeesha
 * Date: August 22, 2024
 * </p>
 *
 * <p>
 * All Rights Reserved by Loker Student Union, Inc at California State University Dominguez Hills from 2024.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates an OpenAPI bean to configure the OpenAPI (Swagger) documentation.
     *
     * <p>
     * The OpenAPI bean defines the metadata for the API documentation, such as the title,
     * description, and version.
     * </p>
     *
     * @return An OpenAPI object configured with API metadata.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LSU Game Room RevOps Nexus API")
                        .description("API documentation for the RevOps Nexus system")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Digvijay Hethur Jagadeesha")
                                .email("dhethurjagadeessha1@toromail.csudh.edu")
                        )
                        .termsOfService("Terms of service")
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("License of API")
                                .url("API license URL"))
                );
    }
}

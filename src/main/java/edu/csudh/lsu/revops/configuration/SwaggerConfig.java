package edu.csudh.lsu.revops.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * SwaggerConfig class is responsible for configuring Swagger for API documentation.
 * </p>
 *
 * <p>
 * This class sets up Swagger to document all REST APIs available in the application.
 * It enables Swagger using the @EnableSwagger2 annotation and provides a Docket bean
 * that specifies the API documentation type and the paths to include in the documentation.
 * </p>
 *
 * <p>
 * Created by: Digvijay Hethur Jagadeesha
 * Date: August 22 2024
 * </p>
 *
 * <p>
 * All Rights Reserved by Loker Student Union, Inc at California State University Dominguez Hills from 2024.
 * </p>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates a Docket bean to configure Swagger.
     *
     * <p>
     * The Docket bean defines which APIs will be documented by Swagger.
     * It uses {@link RequestHandlerSelectors#any()} to select all available APIs
     * and {@link PathSelectors#any()} to document all paths.
     * </p>
     *
     * @return A Docket configured for Swagger 2.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // Select all APIs in the application for documentation
                .apis(RequestHandlerSelectors.any())
                // Document all API paths
                .paths(PathSelectors.any())
                .build();
    }
}

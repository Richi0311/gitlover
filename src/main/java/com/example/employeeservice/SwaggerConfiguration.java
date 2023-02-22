package com.example.employeeservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static Contact SUPPORT_CONT = new Contact("Arif","http://www.google.com","dharwadkar.arif@gmial.com");
    @Bean
    public Docket newAPI(){

        Set producers = new HashSet(Arrays.asList("application/json","application/xml"));
        Set consmer = new HashSet(Arrays.asList("application/json","application/xml"));
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .produces(producers)
                .consumes(consmer);
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder().title("Employee Service")
                .description("Employee Service with Swagger Documenttaion")
                .termsOfServiceUrl("http://www.google.com")
                .contact(SUPPORT_CONT)
                .license("Employee License verion 1.0")
                .licenseUrl("http://www.google.com/Search")
                .version("3.0")
                .build();
    }
}

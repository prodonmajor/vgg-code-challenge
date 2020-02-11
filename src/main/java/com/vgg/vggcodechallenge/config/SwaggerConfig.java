/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author akinw
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket accountingBridgeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("VGG CODE CHALLENGE REST API's")
                .apiInfo(apiInfo())                
                .select()                
                .paths(regex("/api.*"))
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Project VGG - VGG CODE CHALLENGE REST API")
                .description("REST API for VGG Business logic. "
                        + "Refer to use case documentation for more information")
                .termsOfServiceUrl("https://vggtermsofservice.com/")
                .contact(new Contact("Atabo Fred Team", "https://stillpracticing/", "myemail@gmail.com"))
                .license("Proprietary source code")
                .licenseUrl("https://lightsideoftheforce")
                .build();
    }
}

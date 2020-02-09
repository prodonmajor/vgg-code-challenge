/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgg.vggcodechallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
                .forCodeGeneration(true)
                .select().apis(RequestHandlerSelectors.basePackage("com.easycoop.uba.engine.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Project Radical - UBA Engine REST API")
                .description("REST API for UBA engine logic. "
                        + "Refer to use case documentation for more information")
                .termsOfServiceUrl("https://appedevtermsofservice.com/")
                .contact(new Contact("App Dev Team", "https://speaknoevilseenoevil/", "love@forall.com"))
                .license("Proprietary source code")
                .licenseUrl("https://lightsideoftheforce")
                .build();
    }
}

package com.tml.poc.Wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



//@Configuration
public class SpringFoxConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//        
//    }

    
    
//    @Bean
//    public Docket swaggerHealthApi12()
//    {
//        final ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("Authorization")
//                        .description("The HTTP Authorization request header contains the credentials to authenticate a user agent with a server, usually after the server has responded with a 401 Unauthorized status ")
//                        .modelRef(new ModelRef("string")).parameterType("header")
//                        .defaultValue("Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.").required(true).build();
//        final ParameterBuilder contentParameterBuilder = new ParameterBuilder();
//        contentParameterBuilder.name("Content-Type")
//                        .description("The Content-Type entity header is used to indicate the media type of the resource.")
//                        .modelRef(new ModelRef("string")).parameterType("header").defaultValue("application/json").required(true)
//                        .build();
//
//        final ParameterBuilder languageParmBuilder = new ParameterBuilder();
//        languageParmBuilder.name("Accept-Language")
//                        .description("The Accept-Language request HTTP header advertises which languages the client is able to understand, and which locale variant is preferred. (By languages, we mean natural languages, such as English, and not programming languages.).")
//                        .modelRef(new ModelRef("string")).parameterType("header").defaultValue("en-US").required(false).build();
//
//        final java.util.List<Parameter> aParameters = new ArrayList<>();
//        aParameters.add(aParameterBuilder.build());
//        aParameters.add(contentParameterBuilder.build());
//        aParameters.add(languageParmBuilder.build());
//
//        return new Docket(DocumentationType.SWAGGER_2).groupName("health-api-1.2").select()
//                        .apis(RequestHandlerSelectors.basePackage("com.health.management"))
//                        //                        .paths(regex("/health/v1.2.*"))
//                        .build()
//                        //                        .pathMapping("")
//                        .globalOperationParameters(aParameters).apiInfo(new ApiInfoBuilder().version("1.2").title("Health API")
//                                        .description("Documentation Health API v1.2").build());
//    }
    
}
package com.tml.poc.Wallet.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.classmate.TypeResolver;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuring Swagger for API Documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
//	@Bean
//	public Docket api() {
//		final ParameterBuilder aParameterBuilder = new ParameterBuilder();
//
//		aParameterBuilder.name("Authorization").description(
//				"The HTTP Authorization request header contains the credentials to authenticate a user agent with a server, usually after the server has responded with a 401 Unauthorized status ")
//				.modelRef(new ModelRef("string")).parameterType("header")
//				.defaultValue("Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.").required(true).build();
//		final ParameterBuilder contentParameterBuilder = new ParameterBuilder();
//		contentParameterBuilder.name("Content-Type")
//				.description("The Content-Type entity header is used to indicate the media type of the resource.")
//				.modelRef(new ModelRef("string")).parameterType("header").defaultValue("application/json")
//				.required(true).build();
//
//		final ParameterBuilder languageParmBuilder = new ParameterBuilder();
//		languageParmBuilder.name("Accept-Language").description(
//				"The Accept-Language request HTTP header advertises which languages the client is able to understand, and which locale variant is preferred. (By languages, we mean natural languages, such as English, and not programming languages.).")
//				.modelRef(new ModelRef("string")).parameterType("header").defaultValue("en-US").required(false).build();
//
//		final java.util.List<Parameter> aParameters = new ArrayList<>();
//		aParameters.add(aParameterBuilder.build());
//		aParameters.add(contentParameterBuilder.build());
//		aParameters.add(languageParmBuilder.build());
//		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
//				.apis(RequestHandlerSelectors.basePackage("com.tml.poc"))
//
//				.build().globalOperationParameters(aParameters).apiInfo(new ApiInfoBuilder().version("1.2")
//						.title("TML Wallet API").description("Documentation Health API v1.2").build());
//	}

//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.tml.poc.wallet")).build();
//	}

	    @Bean
	    public Docket api(){
	        return  new Docket(DocumentationType.SWAGGER_2);
	    }

	/**
	 * Swagger health API 1.2.
	 * 
	 * @return the docket
	 */
//	    @Bean
//	    public Docket swaggerHealthApi12()
//	    {
//	        final ParameterBuilder aParameterBuilder = new ParameterBuilder();
//	        aParameterBuilder.name("Authorization")
//	                        .description("The HTTP Authorization request header contains the credentials to authenticate a user agent with a server, usually after the server has responded with a 401 Unauthorized status ")
//	                        .modelRef(new ModelRef("string")).parameterType("header")
//	                        .defaultValue("Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.").required(true).build();
//	        final ParameterBuilder contentParameterBuilder = new ParameterBuilder();
//	        contentParameterBuilder.name("Content-Type")
//	                        .description("The Content-Type entity header is used to indicate the media type of the resource.")
//	                        .modelRef(new ModelRef("string")).parameterType("header").defaultValue("application/json").required(true)
//	                        .build();
//
//	        final ParameterBuilder languageParmBuilder = new ParameterBuilder();
//	        languageParmBuilder.name("Accept-Language")
//	                        .description("The Accept-Language request HTTP header advertises which languages the client is able to understand, and which locale variant is preferred. (By languages, we mean natural languages, such as English, and not programming languages.).")
//	                        .modelRef(new ModelRef("string")).parameterType("header").defaultValue("en-US").required(false).build();
//
//	        final java.util.List<Parameter> aParameters = new ArrayList<>();
//	        aParameters.add(aParameterBuilder.build());
//	        aParameters.add(contentParameterBuilder.build());
//	        aParameters.add(languageParmBuilder.build());
//
//	        return new Docket(DocumentationType.SWAGGER_2).groupName("wallet-api-1.2").select()
//	                        .apis(RequestHandlerSelectors.basePackage("com.tml.poc.wallet"))
//	                        //                        .paths(regex("/health/v1.2.*"))
//	                        .build()
//	                        //                        .pathMapping("")
//	                        .globalOperationParameters(aParameters).apiInfo(new ApiInfoBuilder().version("1.2").title("Health API")
//	                                        .description("Documentation Health API v1.2").build());
//	    }

}
//
//@Configuration
//public class SpringFoxConfig {                                    
//    @Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.any())              
//          .paths(PathSelectors.any())                          
//          .build();                                           
//    }
//}x
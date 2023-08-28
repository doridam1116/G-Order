package com.gorder.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.gorder.config.PropertyConfig.getMessageSource;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gaubiz.gorder.api"))
                .build()
                .globalResponses(HttpMethod.GET, GlobalResponses())
                .globalResponses(HttpMethod.POST, GlobalResponses())
                .globalResponses(HttpMethod.DELETE, GlobalResponses())
                .globalResponses(HttpMethod.PATCH, GlobalResponses())
                .globalResponses(HttpMethod.PUT, GlobalResponses());
    }



    private List<Response> GlobalResponses() {
        List<Response> responses = new ArrayList<>();

        responses.add(new ResponseBuilder()
                .code("200")
                .description(getMessageSource().getMessage("HTTP_OK",null,Locale.getDefault()))
                .build());

        responses.add(new ResponseBuilder()
                .code("400")
                .description(getMessageSource().getMessage("HTTP_BAD_REQUEST",null, Locale.getDefault()))
                .build());

        responses.add(new ResponseBuilder()
                .code("404")
                .description(getMessageSource().getMessage("HTTP_NOT_FOUND",null, Locale.getDefault()))
                .build());

        responses.add(new ResponseBuilder()
                .code("500")
                .description(getMessageSource().getMessage("HTTP_SERVER_ERROR",null,Locale.getDefault()))
                .build());

        return responses;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("G-Order v1.0")
                .description("G-Order API")
                .version("1.0")
                .build();
    }
}
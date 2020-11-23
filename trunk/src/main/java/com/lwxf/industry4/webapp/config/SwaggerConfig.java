package com.lwxf.industry4.webapp.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Value("${swagger2.enable}")
        private boolean swagger2Enable;
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .enable(swagger2Enable)
                    .select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //扫描所有有注解的bean
                    .paths(PathSelectors.any()).build().apiInfo(apiInfo())
                    .globalOperationParameters(setHeaderToken());
        }


        private ApiInfo apiInfo() {
            return new ApiInfoBuilder().title("老屋新房 RESTful APIs").description("")
                    .license("The Apache License, Version 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").termsOfServiceUrl("http://my.csdn.net/elvishehai")
                    .version("1.0").build();
        }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("X-ATOKEN").description("token").modelRef(new ModelRef("string")).defaultValue("0cab85f2c9023a6a0d4fed3806e43697").parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }
}

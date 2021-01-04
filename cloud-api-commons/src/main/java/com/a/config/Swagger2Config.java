package com.a.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-12 14:20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("title")
                .description("description")
                .termsOfServiceUrl("http://www.zimug.com")
                .version("1.0")
                .build();
    }

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.a"))
                .paths(PathSelectors.regex("/rest/.*"))
                .build();
    }
}

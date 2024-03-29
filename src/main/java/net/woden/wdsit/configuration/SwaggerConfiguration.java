package net.woden.wdsit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.woden.wdsit.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "DOCUMENTACION WDSIT", //NAME
                "https://www.woden.com.co/", //DESCRIPTION
                "Version 1.0", //VERSION
                "https://www.woden.com.co/", //TERMS OF SERVICE URL
                "https://www.woden.com.co/",
                "Licencia", //LISENSE
                "https://www.woden.com.co/" //TERMS OF LICENSE URL		    
        );
    }
}

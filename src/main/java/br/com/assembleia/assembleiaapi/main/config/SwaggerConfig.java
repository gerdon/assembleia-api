package br.com.assembleia.assembleiaapi.main.config;

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
 * @author Gerdon Mafra
 *
 * http://localhost:8080/swagger-ui.html#/
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket bookHotelApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Assembleia")
                .version("1.0")
                .description("API Assembleia")
                .contact(new Contact("Gerdon Mafra", "", "gerdon_mafra@hotmail.com"))
                .license("Apache License Version 2.0")
                .build();
    }
    
}

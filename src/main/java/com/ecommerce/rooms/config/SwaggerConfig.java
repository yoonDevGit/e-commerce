package com.ecommerce.rooms.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private String title = "REST API";
  private String descrition = "Some custom description of API";
  private String version = "v1";
  private String termsOfServiceUrl = "Terms of service";
  private Contact contact = new Contact("yoon", "yoondev93@gamil.com", "yoondev93@gamil.com");
  private String license = "License of API";
  private String licenseUrl = "API license URL";

  @Bean
  public Docket apiV1() {

    List<ResponseMessage> responseMessages = new ArrayList<>();
    responseMessages(responseMessages);

    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(version)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ecommerce.rooms.api.v1"))
        .paths(PathSelectors.ant("/api/v1/**"))
        .build()
        .apiInfo(apiInfo());
  }

  private void responseMessages(List<ResponseMessage> responseMessages) {
    responseMessages.add(new ResponseMessageBuilder()
        .code(200)
        .message("OK")
        .build());
    responseMessages.add(new ResponseMessageBuilder()
        .code(401)
        .message("Unauthorized")
        .build());
    responseMessages.add(new ResponseMessageBuilder()
        .code(404)
        .message("Forbidden")
        .build());
    responseMessages.add(new ResponseMessageBuilder()
        .code(500)
        .message("Internal Server Error")
        .build());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(title, descrition, version, termsOfServiceUrl, contact, license, licenseUrl,
        Collections.emptyList());
  }
}

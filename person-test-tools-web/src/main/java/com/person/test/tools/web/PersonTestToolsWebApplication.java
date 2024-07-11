package com.person.test.tools.web;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.person.test.tools.**"})
@DubboComponentScan(basePackages = "com.person.test.tools.dubbo.provider.**")
@EnableDubbo
@SpringBootApplication()
public class PersonTestToolsWebApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PersonTestToolsWebApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(PersonTestToolsWebApplication.class, args);

    }
}

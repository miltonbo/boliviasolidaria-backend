package com.bs.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.logging.Logger;


@SpringBootApplication
public class BoliviaSolidariaApiApplication extends SpringBootServletInitializer {

    private static final Logger log = Logger.getLogger(BoliviaSolidariaApiApplication.class.getName());
    
    public static void main(String[] args) {
        SpringApplication.run(BoliviaSolidariaApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        setRegisterErrorPageFilter(false);
        return application.sources(applicationClass);
    }

    private static Class<BoliviaSolidariaApiApplication> applicationClass = BoliviaSolidariaApiApplication.class;
    
}
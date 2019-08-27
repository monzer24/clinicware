package com.clinicware;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SpringBootApplication
@ComponentScan({"com.clinicware.service", "com.clinicware.controller", "com.clinicware.service.api", "com.clinicware.security"})
public class DemoApplication extends SpringBootServletInitializer {

    private static Class<DemoApplication> application = DemoApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(application, args);
        System.out.println("http://localhost:8080/");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(application);
    }

    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }

    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

}

package com.purplemango.app;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import static org.springframework.boot.SpringApplication.run;
@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        // Start the Spring Boot application
        run(MainApp.class, args);
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}

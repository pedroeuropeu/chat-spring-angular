package com.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chat"})
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(ServerProperties serverProperties) {

        return (evt) -> {
            Integer port = serverProperties.getPort();
            
            LOG.info("App running on: http://127.0.0.1:{}", port);
            LOG.info("Swagger can be found on: http://127.0.0.1:{}/swagger-ui.html", port);
        };
    }
    
}
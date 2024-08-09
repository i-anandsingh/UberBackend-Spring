package com.uber.service.socketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@EntityScan("com.uber.service.entityservice.models")
public class SocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketServerApplication.class, args);
    }

}

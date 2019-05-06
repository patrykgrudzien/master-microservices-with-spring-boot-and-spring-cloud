package com.jurik99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class);
    }
}

package com.jurik99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * To call appropriate microservice through API gateway, an address would look like the following:
 * http://localhost:8765/{application-name}/{uri}
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGatewayServerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class);
	}
}

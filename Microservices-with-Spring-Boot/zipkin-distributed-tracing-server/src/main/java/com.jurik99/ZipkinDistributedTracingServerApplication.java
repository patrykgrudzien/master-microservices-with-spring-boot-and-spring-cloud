package com.jurik99;

import zipkin2.server.internal.EnableZipkinServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinDistributedTracingServerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ZipkinDistributedTracingServerApplication.class);
	}
}

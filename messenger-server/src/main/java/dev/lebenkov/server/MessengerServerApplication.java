package dev.lebenkov.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MessengerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessengerServerApplication.class, args);
	}

}
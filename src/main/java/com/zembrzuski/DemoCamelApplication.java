package com.zembrzuski;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCamelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoCamelApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new MyRoute().createRoute());

		context.start();
	}

}

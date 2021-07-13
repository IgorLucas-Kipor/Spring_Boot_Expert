package com.igorlucas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.igorlucas.entity.Cliente;
import com.igorlucas.repository.Clientes;

@SpringBootApplication
public class SpringBootExpertApplication {
	
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired Clientes clientes) {
		return args -> {
			Cliente c = new Cliente(null, "Igor");
			clientes.save(c);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}

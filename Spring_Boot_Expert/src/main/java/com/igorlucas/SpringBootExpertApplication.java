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
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			System.out.println("Salvando clientes.");
			clientes.save(new Cliente(null, "Igor"));
			clientes.save(new Cliente(null, "Lucas"));
			
			boolean exists = clientes.existsByNome("Igor");
			System.out.println("Existe um cliente com esse nome? " + exists);
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}

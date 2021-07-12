package com.igorlucas;

import java.util.List;

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
			
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Atualizando clientes.");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.save(c);
			});
			
			
			clientes.findByNomeLike("ca").forEach(System.out::println);
			
			System.out.println("Deletando clientes.");
			clientes.findAll().forEach(c -> clientes.delete(c));
			
			System.out.println("Buscando clientes.");
			todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}

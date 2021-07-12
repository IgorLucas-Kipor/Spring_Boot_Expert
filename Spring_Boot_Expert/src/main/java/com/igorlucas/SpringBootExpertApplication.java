package com.igorlucas;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.igorlucas.entity.Cliente;
import com.igorlucas.entity.Pedido;
import com.igorlucas.repository.Clientes;
import com.igorlucas.repository.Pedidos;

@SpringBootApplication
public class SpringBootExpertApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			
			System.out.println("Salvando clientes.");
			Cliente igor = new Cliente(null, "Igor");
			Cliente lucas = new Cliente(null, "Lucas");
			clientes.save(igor);
			clientes.save(lucas);
			
			Pedido p = new Pedido();
			p.setCliente(igor);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidos.save(p);
			
			pedidos.findByCliente(igor).forEach(System.out::println);
			
//			Cliente cliente = clientes.findClienteFetchPedidos(igor.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}

package com.igorlucas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igorlucas.entity.Cliente;
import com.igorlucas.repository.Clientes;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	

}

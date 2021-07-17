package com.igorlucas.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.igorlucas.entity.Cliente;
import com.igorlucas.repository.Clientes;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@GetMapping(value = "/{id}")
//	@ApiOperation("Obter detalhes de um cliente")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Cliente encontrado!"),
//		@ApiResponse(code = 404, message = "Cliente não encontrado para o id passado.")
//	})
	public Cliente getClienteById(@PathVariable Integer id) {

		return clientes.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

	}
	
	//usar o @ApiParam ("texto a mostrar") customiza a descrição de um parâmetro passado ao método

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/{id}")
//	@ApiOperation("Salva um novo cliente")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Cliente salvo com sucesso!"),
//		@ApiResponse(code = 404, message = "Falha no salvamento.")
//	})
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clientes.save(cliente);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clientes.findById(id).map(cliente -> {
			clientes.delete(cliente);
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Cliente update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		return clientes.findById(id).map(c -> {
			cliente.setId(c.getId());
			clientes.save(cliente);
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<Cliente> example = Example.of(filtro, matcher);
		return clientes.findAll(example);
	}

}

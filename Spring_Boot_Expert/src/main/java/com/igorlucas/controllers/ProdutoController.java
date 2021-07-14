package com.igorlucas.controllers;

import java.util.List;

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

import com.igorlucas.entity.Produto;
import com.igorlucas.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private Produtos produtos;
	
	@GetMapping(value = "/{id}")
	public Produto findById(@PathVariable Integer id) {
		return produtos.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto create(@RequestBody Produto produto) {
		return produtos.save(produto);
	}
	
	@PutMapping(value = "/{id}")
	public Produto update(@RequestBody Produto produto, @PathVariable Integer id) {
		return produtos.findById(id)
				.map(p -> {
					produto.setId(p.getId());
					produtos.save(produto);
					return produto;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtos.findById(id)
				.map(p -> {
					produtos.delete(p);
					return p;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@GetMapping
	public List<Produto> findByName(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Produto> exemplo = Example.of(filtro, matcher);
		return produtos.findAll(exemplo);
	}
	
	

}

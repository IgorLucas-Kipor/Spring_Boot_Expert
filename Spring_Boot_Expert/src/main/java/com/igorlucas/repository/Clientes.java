package com.igorlucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igorlucas.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String nome);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	
	Cliente findOneByNome(String nome);
	
	boolean existsByNome(String nome);
	
	/*
	 * Parâmetros devem ser inseridos na ordem definida nos query methods
	 */

}

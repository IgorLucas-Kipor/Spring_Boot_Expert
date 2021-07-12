package com.igorlucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igorlucas.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String nome);	

}

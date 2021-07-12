package com.igorlucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.igorlucas.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	
	@Query(value = "select c from Cliente c where c.nome like %:nome%")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	/*
	 * Para fazer a consulta usando SQL nativo
	 * 
	 * 	@Query(value = "select * from cliente c where c.nome like '%:nome%'", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	 */
	
	@Query(value = "delete from Cliente c where c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);
	
	/*
	 * Parâmetros devem ser inseridos na ordem definida nos query methods
	 */

}

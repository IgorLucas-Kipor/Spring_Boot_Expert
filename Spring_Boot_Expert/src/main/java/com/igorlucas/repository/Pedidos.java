package com.igorlucas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.igorlucas.entity.Cliente;
import com.igorlucas.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
	List<Pedido> findByCliente(Cliente cliente);
	
	@Query("select p from Pedido p left join fetch p.items where p.id = :id")
	Optional<Pedido> findByIdFetchItems(@Param("id") Integer id);
}

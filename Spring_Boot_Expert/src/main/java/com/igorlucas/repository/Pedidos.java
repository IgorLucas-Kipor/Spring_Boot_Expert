package com.igorlucas.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igorlucas.entity.Cliente;
import com.igorlucas.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
	Set<Pedido> findByCliente(Cliente cliente);
}

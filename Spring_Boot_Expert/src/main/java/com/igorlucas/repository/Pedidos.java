package com.igorlucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igorlucas.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

}

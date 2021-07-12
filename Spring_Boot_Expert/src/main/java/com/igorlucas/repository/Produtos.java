package com.igorlucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igorlucas.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{

}

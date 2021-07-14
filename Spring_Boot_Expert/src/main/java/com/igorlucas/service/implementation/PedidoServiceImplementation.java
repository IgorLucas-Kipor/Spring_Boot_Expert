package com.igorlucas.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorlucas.repository.Pedidos;
import com.igorlucas.service.PedidoService;

@Service
public class PedidoServiceImplementation implements PedidoService {

	@Autowired
	private Pedidos pedidos;
	
	
}

package com.igorlucas.service;

import com.igorlucas.entity.Pedido;
import com.igorlucas.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);

}

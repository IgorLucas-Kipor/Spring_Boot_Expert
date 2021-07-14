package com.igorlucas.service;

import com.igorlucas.dto.PedidoDTO;
import com.igorlucas.entity.Pedido;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);

}

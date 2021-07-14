package com.igorlucas.service;

import java.util.Optional;

import com.igorlucas.entity.Pedido;
import com.igorlucas.enums.StatusPedido;
import com.igorlucas.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer idPedido);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);

}

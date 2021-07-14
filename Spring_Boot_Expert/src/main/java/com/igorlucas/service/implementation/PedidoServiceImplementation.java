package com.igorlucas.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorlucas.dto.ItemPedidoDTO;
import com.igorlucas.dto.PedidoDTO;
import com.igorlucas.entity.Cliente;
import com.igorlucas.entity.ItemPedido;
import com.igorlucas.entity.Pedido;
import com.igorlucas.entity.Produto;
import com.igorlucas.exceptions.RegraNegocioException;
import com.igorlucas.repository.Clientes;
import com.igorlucas.repository.ItemsPedidos;
import com.igorlucas.repository.Pedidos;
import com.igorlucas.repository.Produtos;
import com.igorlucas.service.PedidoService;

@Service
public class PedidoServiceImplementation implements PedidoService {

	@Autowired
	private Pedidos pedidos;

	@Autowired
	private Clientes clientes;

	@Autowired
	private Produtos produtos;
	
	@Autowired
	private ItemsPedidos itemsPedidos;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientes.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inexistente ou inválido: " + idCliente));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		Set<ItemPedido> itemsConvertidos = converterItems(pedido, dto.getItems());
		pedidos.save(pedido);
		itemsPedidos.saveAll(itemsConvertidos);
		pedido.setItems(itemsConvertidos);

		return pedido;

	}

	private Set<ItemPedido> converterItems(Pedido pedido, Set<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Lista de items vazia.");
		}
		
		return items.stream().map(dto -> {
			
			Integer idProduto = dto.getProduto();
			Produto produto = produtos
					.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inexistente ou inválido: " + idProduto));
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toSet());
	}

}

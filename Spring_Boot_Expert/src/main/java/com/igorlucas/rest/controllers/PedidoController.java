package com.igorlucas.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.igorlucas.entity.ItemPedido;
import com.igorlucas.entity.Pedido;
import com.igorlucas.enums.StatusPedido;
import com.igorlucas.rest.dto.AtualizacaoStatusPedidoDto;
import com.igorlucas.rest.dto.InformacaoItemPedidoDto;
import com.igorlucas.rest.dto.InformacoesPedidoDto;
import com.igorlucas.rest.dto.PedidoDTO;
import com.igorlucas.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid PedidoDTO pedidoDto) {
		Pedido pedido = service.salvar(pedidoDto);
		return pedido.getId();
	}
	
	@GetMapping("/{id}")
	public InformacoesPedidoDto getById(@PathVariable Integer id) {
		return service.obterPedidoCompleto(id)
				.map(p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDto dto) {
		String novoStatus = dto.getNovoStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InformacoesPedidoDto converter(Pedido pedido) {
		return InformacoesPedidoDto.builder()
		.codigo(pedido.getId())
		.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
		.cpf(pedido.getCliente().getCpf())
		.nomeCliente(pedido.getCliente().getNome())
		.total(pedido.getTotal())
		.items(converter(pedido.getItems()))
		.status(pedido.getStatus().name())
		.build();
	}
	
	private List<InformacaoItemPedidoDto> converter(List<ItemPedido> items) {
		if(CollectionUtils.isEmpty(items)) {
			return Collections.emptyList();
		} else {
			return items.stream().map(i -> InformacaoItemPedidoDto
					.builder()
					.descricaoProduto(i.getProduto().getDescricao())
					.precoUnitario(i.getProduto().getPreco())
					.quantidade(i.getQuantidade())
					.build())
					.collect(Collectors.toList());
		}
	}

}

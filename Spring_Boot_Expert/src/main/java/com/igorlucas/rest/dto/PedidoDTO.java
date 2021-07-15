package com.igorlucas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.igorlucas.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "Código do cliente é obrigatório.")
	private Integer cliente;
	
	@NotNull(message = "Informe o valor total do pedido.")
	private BigDecimal total;
	
	@NotEmptyList(message = "A lista deve ser passada com items.")
	private List<ItemPedidoDTO> items;
	

}

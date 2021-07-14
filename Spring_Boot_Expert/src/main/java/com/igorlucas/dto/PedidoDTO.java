package com.igorlucas.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private Set<ItemPedidoDTO> items;
	

}

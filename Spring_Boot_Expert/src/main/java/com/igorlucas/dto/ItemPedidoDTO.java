package com.igorlucas.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemPedidoDTO {
	
	private Integer produto;
	private Integer quantidade;

}

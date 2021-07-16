package com.igorlucas.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private Integer id;
	
	@Column(name= "descricao")
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@Column(name= "preco_unitario")
	@NotNull(message = "{campo.preco.obrigatorio}")
	private BigDecimal preco;
	
}

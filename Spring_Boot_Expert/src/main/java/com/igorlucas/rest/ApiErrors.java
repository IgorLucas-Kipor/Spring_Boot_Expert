package com.igorlucas.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	@Getter
	private List<String> errors;
	
	public ApiErrors(List<String> mensagensErro) {
		this.errors = mensagensErro;
	}

	public ApiErrors(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);
	}
}

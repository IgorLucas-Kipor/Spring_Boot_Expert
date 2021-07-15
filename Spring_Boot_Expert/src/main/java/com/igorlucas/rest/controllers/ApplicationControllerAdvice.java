package com.igorlucas.rest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.igorlucas.exceptions.PedidoNaoEncontradoException;
import com.igorlucas.exceptions.RegraNegocioException;
import com.igorlucas.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex
				.getBindingResult()
				.getAllErrors()
				.stream()
				.map(e -> e.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiErrors(errors);
	}

}

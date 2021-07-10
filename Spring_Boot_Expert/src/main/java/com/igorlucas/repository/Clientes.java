package com.igorlucas.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.igorlucas.entity.Cliente;

@Repository
public class Clientes {
	

	private static String INSERT = "insert into cliente (nome) values (?) ";
	private static String SELECT_ALL = "select * from cliente";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Cliente salvar(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
		return cliente;
	}
	
	public List<Cliente> obterTodos() {
		return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Cliente (Integer.valueOf(resultSet.getString("id")), resultSet.getString("nome"));
			}
		});
	}
}

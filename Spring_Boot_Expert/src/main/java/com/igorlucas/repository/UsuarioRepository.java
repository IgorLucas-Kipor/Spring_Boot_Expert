package com.igorlucas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igorlucas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByLogin(String login);

}

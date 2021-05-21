package com.bookstore.com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.com.bookstore.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<com.bookstore.com.bookstore.model.Usuario> findByNome(String nome);
	
}

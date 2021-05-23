package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Usuario;
import com.bookstore.com.bookstore.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
}

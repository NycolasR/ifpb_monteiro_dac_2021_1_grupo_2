package com.bookstore.com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Usuario;
import com.bookstore.com.bookstore.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario) throws Exception {
		if(!verificarExistencia(usuario)) {
			usuarioRepository.save(usuario);			
		}else {
			String mensagem = "Já existe um usuario cadastrado com o email " + usuario.getEmail();
			throw new Exception(mensagem);			
		}
	}
	
	
	public void excluir(Usuario usuario) {
		usuarioRepository.delete(usuario);		
	}
	
	public void excluirTudo() {
		usuarioRepository.deleteAll();
	}
	
	public List<Usuario> usuarioPorEmail(String email){
		return (List<Usuario>) usuarioRepository.findByEmailIs(email);
	}
	
	private boolean verificarExistencia(Usuario usuario) {
		List<Usuario> userTeste = usuarioPorEmail(usuario.getEmail());
		if(userTeste.isEmpty()) 
			return false;
		
		return true;
	}
	
}

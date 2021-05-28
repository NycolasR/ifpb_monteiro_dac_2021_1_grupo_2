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
		if(verificarExistencia(usuario)) {
			usuarioRepository.save(usuario);			
		}else {
			String mensagem = "JÃ¡ existe um usuario cadastrado com o email " + usuario.getEmail();
			throw new Exception(mensagem);			
		}
	}
	
	public void atualizar(Usuario usuario) throws Exception {
		Usuario userTemp = usuarioPorEmail(usuario.getEmail());
		if(userTemp.equals(usuario)) {
			usuarioRepository.save(usuario);
		}else {
			throw new Exception("O Email nÃ£o pode ser altereado");
		}
	}
	
	public void excluir(String email) throws Exception {
		usuarioRepository.delete(usuarioPorEmail(email));		
	}
	
	public void excluirTudo() {
		usuarioRepository.deleteAll();
	}
	
	public Usuario usuarioPorEmail(String email)throws Exception{
		if(usuarioRepository.findByEmailIs(email).size() >= 1) 
			return (Usuario) usuarioRepository.findByEmailIs(email).get(0);
		
		throw new Exception("NÃ£o ha nenhum usuario com o email: " + email + " cadastrado.");
		
	}
	
	public List<Usuario> usuarioPorNome(String nome)throws Exception{
		List<Usuario> usersTemp = usuarioRepository.findByNome(nome);
		if(usersTemp.size()>=1)
			return usersTemp;
		
		throw new Exception("Nenhum usuario com o nome " + nome + " encontrado");
	}
	
	private boolean verificarExistencia(Usuario usuario) {
		try {
			usuarioPorEmail(usuario.getEmail());
		}catch (Exception e){
			return true;
		}
		
		return false;
	}
	
}

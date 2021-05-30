package com.bookstore.com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Endereco;
import com.bookstore.com.bookstore.model.Usuario;
import com.bookstore.com.bookstore.service.UsuarioService;

@Component
public class FacadeUsuarios {
	
	@Autowired
	private UsuarioService usuarioService;

	public Usuario cadastrarUsuario(String nome, String email, String senha) throws Exception {
		Usuario usuarioTemp = new Usuario();
		usuarioTemp.setNome(nome);
		usuarioTemp.setEmail(email);
		usuarioTemp.setSenha(senha);
		usuarioTemp.setAdmin(false);
		if(usuarioService.quantidadeDeUsuariosCadastrados() == 0) {
			usuarioTemp.setAdmin(true);
		}
		
		usuarioService.salvar(usuarioTemp);
		return usuarioService.usuarioPorEmail(email);
	}
	
	public void addEndereco(String emailUsuario , Endereco endereco) throws Exception {
		Usuario userTemp = usuarioService.usuarioPorEmail(emailUsuario);
		userTemp.addEndereco(endereco);
		usuarioService.atualizar(userTemp);
	}
	
	public boolean verificarExistencia(String email) {
		return usuarioService.verificarExistencia(email);
	}
	
	
	
}
 
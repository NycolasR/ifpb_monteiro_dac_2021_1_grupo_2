package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.model.Endereco;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerUsuario {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/perfil/{id}")
	public String recuperarPaginaPerfil(@PathVariable("id") Long id, Model model) {
	
		Usuario usuario;
		List<Endereco> enderecos;
		
		try {
			usuario = usuarioService.recuperarPeloId(id);
			enderecos = usuario.getEnderecos();
			
			model.addAttribute("usuario", usuario);
			model.addAttribute("enderecos", enderecos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/profile";
	}
	
	
	/*
	 * oefkf
	 * dsafD
	 *
	 * 
	 *
	 * 
	 * 
	 */
}

package com.bookstore.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.model.Endereco;
import com.bookstore.model.Pedido;
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
		Set<Pedido> pedidos;
		
		try {
			usuario = usuarioService.recuperarPeloId(id);
			enderecos = usuario.getEnderecos();
			pedidos = usuario.getPedidos();
			
			model.addAttribute("usuario", usuario);
			model.addAttribute("enderecos", enderecos);
			model.addAttribute("pedidos", pedidos);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "user/profile";
	}
	
	@PostMapping("/usuario_update/{id}")
	public String atualizarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, @PathVariable("id") Long id) {
		
		if(result.hasErrors()) {
			return "user/profile";
		}
		
		try {
			Usuario usuarioSalvo = usuarioService.recuperarPeloId(id);
			
			usuarioSalvo.setEmail(usuario.getEmail());
			usuarioSalvo.setNome(usuario.getNome());
			
			usuarioService.atualizar(usuarioSalvo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + id;
	}
}

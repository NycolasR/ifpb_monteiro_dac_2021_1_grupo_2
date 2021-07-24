package com.bookstore.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeEnderecos;
import com.bookstore.model.Endereco;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerUsuario {
	
	@Autowired
	private FacadeEnderecos facadeEnderecos;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private Long idUsuario;
	
	@GetMapping("/perfil/{id}")
	public String recuperarPaginaPerfil(@PathVariable("id") Long id, Model model) {
	
		Usuario usuario;
		List<Endereco> enderecos;
		Set<Pedido> pedidos;
		
		try {
			usuario = usuarioService.recuperarPeloId(id);
			enderecos = usuario.getEnderecos();
			pedidos = usuario.getPedidos();
			
			this.idUsuario = id;
			
			model.addAttribute("usuario", usuario);
			model.addAttribute("enderecos", enderecos);
			model.addAttribute("pedidos", pedidos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "user/profile";
	}
	
	@PostMapping("/usuario_update/{id}")
	public String atualizarUsuario(@ModelAttribute Usuario usuario, @PathVariable("id") Long id) {
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
	
	
	@GetMapping("/endereco_form/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
		System.err.println(id);
		
		try {
			if(id > 0) {//os ids cadastrados no banco são maiores que 0
				model.addAttribute("endereco", facadeEnderecos.recuperarEndereco(id));
			} else {
				model.addAttribute("endereco", facadeEnderecos.recuperarEnderecoNulo());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "enderecos/enderecos_form";
	}
	
	@PostMapping("/endereco_form_add")
	public String criarEndereco(@ModelAttribute Endereco endereco) {
		
		Usuario usuario;
		System.out.println(endereco);
		try {
			usuario = usuarioService.recuperarPeloId(idUsuario);
			usuario.addEndereco(endereco);
			
			facadeEnderecos.criarEndereco(endereco);
			
			usuarioService.atualizar(usuario);
			facadeEnderecos.atualizarEndereco(endereco, endereco.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + idUsuario;
	}
	
	@PostMapping("/endereco_form_update/{id}")
	public String atualizarEndereco(@ModelAttribute Endereco endereco, @PathVariable("id") Long id) {
		
		
		try {
			facadeEnderecos.atualizarEndereco(endereco, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + idUsuario;
	}
	
	@PostMapping("/endereco_form_remove/{id}")
	public String removerEndereco(@PathVariable("id") Long id) {
		
		try {
			facadeEnderecos.removerAutor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + idUsuario;
	}
	
	/*
	  oef
	 

	 *

	 * 
	 * 
	 */
}

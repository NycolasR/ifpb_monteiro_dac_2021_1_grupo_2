package com.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeEnderecos;
import com.bookstore.model.Endereco;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerCrudEnderecos {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FacadeEnderecos facadeEnderecos;

	@GetMapping("/voltar")
	public String voltarParaTelaPerfil(@AuthenticationPrincipal Usuario usuario) {
		return "redirect:/perfil/" + usuario.getId();
	}

	@GetMapping("/endereco_form/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
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
	public String criarEndereco(
			@Valid @ModelAttribute Endereco endereco, 
			BindingResult result, 
			@AuthenticationPrincipal Usuario usuario) {
		
		if(result.hasErrors()) {
			return "enderecos/enderecos_form";
		}
		
		try {
			usuario.addEndereco(endereco);
			facadeEnderecos.criarEndereco(endereco);
			usuarioService.atualizar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + usuario.getId();
	}
	
	@PostMapping("/endereco_form_update/{id}")
	public String atualizarEndereco(
			@Valid @ModelAttribute Endereco endereco, 
			BindingResult result, @PathVariable("id") Long id,
			@AuthenticationPrincipal Usuario usuario) {
		
		if(result.hasErrors()) {
			return "enderecos/enderecos_form";
		}
		
		try {
			facadeEnderecos.atualizarEndereco(endereco, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + usuario.getId();
	}
	
	@PostMapping("/endereco_form_remove/{id}")
	public String removerEndereco(
			@PathVariable("id") Long id, 
			@AuthenticationPrincipal Usuario usuario) {
		
		try {
			facadeEnderecos.removerEndereco(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil/" + usuario.getId();
	}
	
}

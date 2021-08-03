package com.bookstore.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeUsuarios;
import com.bookstore.model.Endereco;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerUsuario {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FacadeUsuarios facadeUsuarios;

	@GetMapping("/perfil")
	public String recuperarPaginaPerfil(@AuthenticationPrincipal Usuario usuario, Model model) {
	
		List<Endereco> enderecos;
		Set<Pedido> pedidos;

		try {
			usuario = usuarioService.recuperarPeloId(usuario.getId());
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
	
	@PostMapping("/usuario_create")
	public String cadastrarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		
		if(facadeUsuarios.estaPresenteNoBd(usuario.getEmail())) {
			model.addAttribute("excecao", "Já existe um cliente com este email cadastrado!\nPor gentileza, use outro email.");
			return "cadastro/cadastro";
		}
		
		if(result.hasErrors()) {
			model.addAttribute("excecao", "");
			return "cadastro/cadastro";
		}
		
		String senhaEncriptada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		
		try {
			facadeUsuarios.cadastrarUsuario(usuario.getNome(), usuario.getEmail(), senhaEncriptada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/inicio";
	}
	
	@PostMapping("/usuario_update/{id}")
	public String atualizarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, @PathVariable("id") Long id) {
		
		Usuario usuarioSalvo = null;
		try {
			usuarioSalvo = usuarioService.recuperarPeloId(id);
			usuario.setSenha(usuarioSalvo.getSenha());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(result.hasErrors()) {
			return "user/profile";
		}
		
		usuarioSalvo.setEmail(usuario.getEmail());
		usuarioSalvo.setNome(usuario.getNome());
		
		try {
			usuarioService.atualizar(usuarioSalvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil";
	}
}

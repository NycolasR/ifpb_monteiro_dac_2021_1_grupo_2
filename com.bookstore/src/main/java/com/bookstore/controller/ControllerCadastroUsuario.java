package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.model.Usuario;

@Controller
public class ControllerCadastroUsuario {
	
	@GetMapping("/cadastro")
	public String resgatarPaginaCadastro(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("excecao", "");
		return "cadastro/cadastro";
	}
}

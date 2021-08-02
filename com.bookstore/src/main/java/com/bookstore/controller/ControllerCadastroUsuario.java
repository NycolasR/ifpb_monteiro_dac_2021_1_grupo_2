package com.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerCadastroUsuario {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/cadastre-se")
	public String resgatarPaginaCadastro(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "cadastro/cadastre-se";
	}

	@PostMapping("/cadastro")
	public String atualizarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result) {
		
//		if(result.hasErrors()) {
//			return "user/profile";
//		}
		
		try {
			System.err.println("Cheguei ao método");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/inicio";
	}
}

package com.bookstore.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.model.Usuario;

@Controller
public class ControllerTelaInicial {

	@GetMapping("/inicio")
	public String recuperarTelaInicial(Model model, @AuthenticationPrincipal Usuario usuario) {
		model.addAttribute("usuario", usuario);
		
		return "telasiniciais/tela-inicial";
	}
}

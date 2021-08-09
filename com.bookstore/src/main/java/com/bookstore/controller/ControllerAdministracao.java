package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.service.RegistroVendasService;

@Controller
public class ControllerAdministracao {
	
	@Autowired
	private RegistroVendasService registroVendasService;

	@GetMapping("/administracao")
	public String recuperarAdministracao(Model model) {
		
		model.addAttribute("quantidade_vendas", registroVendasService.quantidadeDeVendas());
		
		return "administracao/administracao";
	}
}

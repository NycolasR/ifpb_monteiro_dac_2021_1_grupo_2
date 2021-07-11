package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTelaInicial {

	@GetMapping("/inicio")
	public String recuperarTelaInicial() {
		
		return "telasiniciais/tela-inicial";
	}
	
}

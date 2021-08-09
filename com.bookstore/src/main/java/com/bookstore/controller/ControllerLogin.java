package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author NPG
 * Classe Controller responsável pela rota GET que resulta na tela de
 * login do sistema.
 */
@Controller
public class ControllerLogin {

	/**
	 * Método que é acionado quando a rota /login é acionada.
	 * @return String com o path do arquivo html que deve ser aberto no navegador.
	 */
	@GetMapping("/login")
	public String resgatarPaginaLogin() {
		return "login/login";
	}

}

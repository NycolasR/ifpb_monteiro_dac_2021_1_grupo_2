package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author NPG
 * Classe Controller responsável pela rota GET que resulta na tela de
 * erro padrão do sistema.
 */
@Controller
public class ControllerTelaErro {

	/**
	 * Esta requisição é acionada automaticamente pelo sistema
	 * em casos de erros ocorrerem em tempo de execução
	 * @return "error" que faz referência à página personalizada
	 * error.html
	 */
	@GetMapping("/error")
	public String retornarPaginaErro() {
		
		return "error";
	}
}

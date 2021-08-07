package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.model.Usuario;

/**
 * 
 * @author NPG
 * Classe Controller responsável pela rota GET que resulta na tela de
 * cadastro de novos usuários no sistema. 
 */
@Controller
public class ControllerCadastroUsuario {
	
	/**
	 * Método que é acionado quando a rota /cadastro é acionada.
	 * @param model Atributo usado para manipulação e transferência
	 * dos dados entrea view e o controller
	 * @return String com o path do arquivo html que deve ser aberto no navegador.
	 */
	@GetMapping("/cadastro")
	public String resgatarPaginaCadastro(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		// Atributo responsável por armazenar a mensagem da exceção
		// lançada e, com isto, exibir ao usuário.
		model.addAttribute("excecao", "");
		
		return "cadastro/cadastro";
	}
}

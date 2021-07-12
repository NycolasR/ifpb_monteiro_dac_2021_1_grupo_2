package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerAdministracao {

	@GetMapping("/administracao")
	public String recuperarAdministracao() {
		
		return "administracao/administracao";
	}
}

package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerCrudAutores {

	@GetMapping("/autor")
	public String recuperarAutores() {
		
		return "autores/autores";
	}
	
}

package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerCrudEditoras {

	@GetMapping("/editora")
	public String recuperarEditoras() {
		
		return "editoras/editoras";
	}
}

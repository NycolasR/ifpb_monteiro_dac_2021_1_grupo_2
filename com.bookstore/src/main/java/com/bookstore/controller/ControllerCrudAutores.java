package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.model.Autor;
import com.bookstore.service.AutorService;

@Controller
public class ControllerCrudAutores {

	@Autowired
	private AutorService autorService;

	@GetMapping("/autor")
	public String recuperarAutores(Model model) {

		List<Autor> autores = autorService.recuperarAutores();
		model.addAttribute("autores", autores);

		return "autores/autores";
	}

}

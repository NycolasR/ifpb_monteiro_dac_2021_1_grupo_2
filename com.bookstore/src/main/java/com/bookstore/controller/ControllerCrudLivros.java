package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.model.Livro;
import com.bookstore.service.LivroService;

@Controller
public class ControllerCrudLivros {
	
	@Autowired
	private LivroService livroService;

	@GetMapping("/livro")
	public String listarLivros(Model model) {
		
		List<Livro> livros = livroService.listarLivros();
		model.addAttribute("livros", livros);
		
		return "livros/livros";
	}
	
}

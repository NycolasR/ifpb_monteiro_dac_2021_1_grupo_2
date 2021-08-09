package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.facades.FacadeLivros;

@Controller
public class ControllerTelaDetalhes {
	
	@Autowired
	private FacadeLivros facadeLivros;

	@GetMapping("/mostrar_detalhes/{id}")
	public String mostrarDetalhes(@PathVariable Long id, Model model) {
		
		try {
			model.addAttribute("livro", facadeLivros.recuperarLivro(id));
			model.addAttribute("excecao", "");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			model.addAttribute("excecao", "Infelizmente os detalhes desse livro não foram encontrados!");
		}
				
		return "livros/detalhes_livro";
	}
}

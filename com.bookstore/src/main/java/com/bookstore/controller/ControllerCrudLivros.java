package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.facades.FacadeLivros;

@Controller
public class ControllerCrudLivros {

	@Autowired
	private FacadeLivros facadeLivros;

	@GetMapping("/livro")
	public String recuperarLivros(Model model) {

		model.addAttribute("livros", facadeLivros.recuperarLivros());

		return "livros/livros";
	}

	@GetMapping("/livroform/{id}")
	public String recuperarFormulario(@PathVariable("id") Long id, Model model) {

		try {

			if (id > 0) {// os ids cadastrados no banco são maiores que 0

				model.addAttribute("livro", facadeLivros.recuperarLivro(id));
			} else {
				model.addAttribute("livro", facadeLivros.recuperarLivroNulo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "livros/livrosform";
	}

}

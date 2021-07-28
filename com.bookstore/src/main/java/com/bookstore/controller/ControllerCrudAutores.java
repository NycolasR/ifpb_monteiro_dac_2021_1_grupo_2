package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeAutor;
import com.bookstore.model.Autor;

@Controller
public class ControllerCrudAutores {
	
	@Autowired
	private FacadeAutor facadeAutor;

	@GetMapping("/autor")
	public String recuperarAutores(Model model) {
		
		model.addAttribute("autores", facadeAutor.recuperarAutores());
		
		return "autores/autores";
	}
	
	@GetMapping("/autorform/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
		try {
			
			if(id > 0) {//os ids cadastrados no banco são maiores que 0				
				model.addAttribute("autor", facadeAutor.recuperarAutor(id));
			}else {
				model.addAttribute("autor", facadeAutor.recuperarAutorNulo());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("autor", facadeAutor.recuperarAutorNulo());
			
		}
				
		return "autores/autoresform";
	}
	
	@PostMapping("/autorformadd")
	public String criarAutor(@ModelAttribute Autor autor) {
		
		try {
			facadeAutor.criarAutor(autor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}
	
	@PostMapping("/autorformupdate/{id}")
	public String atualizarAutor(@ModelAttribute Autor autor, @PathVariable("id") Long id) {
		
		
		try {
			facadeAutor.atualizarAutor(autor, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}
	
	@PostMapping("/autorformremove/{id}")
	public String removerAutor(@PathVariable("id") Long id) {
		
		try {
			facadeAutor.removerAutor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}

}

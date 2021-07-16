package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeAutor;
import com.bookstore.model.Autor;
import com.bookstore.service.AutorService;

@Controller
public class ControllerCrudAutores {

	@Autowired
	private AutorService autorService;
	
	@Autowired
	private FacadeAutor facadeAutor;

	@GetMapping("/autor")
	public String recuperarAutores(Model model) {

		List<Autor> autores = autorService.recuperarAutores();
		
		model.addAttribute("autores", autores);
		
		return "autores/autores";
	}
	
	@GetMapping("/autorform/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
		Autor autor = new Autor("");
		
		if(id > 0) {
			 
			 autor = autorService.recuperarPeloId(id).get();
		}
		
		model.addAttribute("autor",autor);
		
		return "autores/autoresform";
	}
	
	@PostMapping("/autorformadd")
	public String criarAutor(@ModelAttribute Autor autor) {
		
		try {
			facadeAutor.criarAutor(autor.getNome());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}
	
	@PostMapping("/autorformupdate/{id}")
	public String atualizarAutor(@ModelAttribute Autor autor, @PathVariable("id") Long id) {
		
		
		try {
			autor.setID(id);
			facadeAutor.atualizarAutor(autor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}
	
	@PostMapping("/autorformremove/{id}")
	public String removerAutor(@PathVariable("id") Long id) {
		
		try {
			facadeAutor.removerAutor(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}

}

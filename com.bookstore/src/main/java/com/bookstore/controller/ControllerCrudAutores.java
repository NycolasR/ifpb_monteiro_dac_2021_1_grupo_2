package com.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	private Long idFornecido;
	private Integer pagina = 1;
		

	@GetMapping("/autor")
	public String recuperarAutores(Model model) {
		
		try {
			Page<Autor> autores = facadeAutor.paginarAutores("nome", Direction.ASC, pagina);
			
			model.addAttribute("autores", autores);
			model.addAttribute("numeracao", facadeAutor.criarPaginacao(autores.getTotalPages(), pagina));
			model.addAttribute("fim", autores.getTotalPages());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "autores/autores";
	}
	
	@GetMapping("/autorform/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
		try {
			
			if(id > 0) {//os ids cadastrados no banco são maiores que 0				
				model.addAttribute("autor", facadeAutor.recuperarAutor(id));
				idFornecido = id;
			}else {
				model.addAttribute("autor", facadeAutor.recuperarAutorNulo());
				idFornecido = id;
			}
			
			model.addAttribute("excecao","");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("autor", facadeAutor.recuperarAutorNulo());
			model.addAttribute("excecao",e.getMessage());
			idFornecido = 0L;
			
		}
		
		model.addAttribute("isAdicionar", idFornecido);
				
		return "autores/autoresform";
	}
	
	@PostMapping("/autorformadd")
	public String criarAutor(@Valid @ModelAttribute Autor autor, BindingResult result, Model model) {
				
		if(result.hasErrors()) {
			model.addAttribute("isAdicionar", 0);
			model.addAttribute("excecao","");
			return "autores/autoresform";
		}
		
		try {
			facadeAutor.criarAutor(autor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/autor";
	}
	
	@PostMapping("/autorformupdate")
	public String atualizarAutor(@Valid @ModelAttribute Autor autor, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("excecao","");
			return "autores/autoresform";
		}
		
		try {
			facadeAutor.atualizarAutor(autor, idFornecido);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/autorform/"+idFornecido;
		}
		
		return "redirect:/autor";
	}
	
	@PostMapping("/autorformremove")
	public String removerAutor() {
		
		try {
			facadeAutor.removerAutor(idFornecido);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/autorform/"+idFornecido;
		}
		
		return "redirect:/autor";
	}
	
	@GetMapping("/voltar_autor")
	public String voltarParaTelaLivros() {
		
		return "redirect:/autor";
	}
	
	@GetMapping("/ecolher_pagina_autor/{id}")
	public String escolherPagina(@PathVariable Integer id) {
		
		pagina = id;
		
		return "redirect:/autor";
	}

}

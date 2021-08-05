package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.facades.FacadeCategoria;
import com.bookstore.facades.FacadeLivros;
import com.bookstore.model.Livro;
import com.bookstore.model.Usuario;

@Controller
public class ControllerTelaInicial {

	@Autowired
	private FacadeLivros facadeLivros;
	
	@Autowired
	private FacadeCategoria facadeCategoria;
	
	private Integer pagina = 1;
	
	@GetMapping("/inicio")
	public String recuperarTelaInicial(Model model, @AuthenticationPrincipal Usuario usuario) {
		
		model.addAttribute("usuario", usuario);
		
		try {
			
			Page<Livro> livros = facadeLivros.paginarLivros("titulo", Direction.ASC, pagina, false);
			
			System.out.println( facadeCategoria.recuperarCategorias());
			
			model.addAttribute("categorias", facadeCategoria.recuperarCategorias());
			model.addAttribute("livros", livros);
			model.addAttribute("numeracao", facadeLivros.criarPaginacao(livros.getTotalPages(), pagina));
			model.addAttribute("fim", livros.getTotalPages());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return "telasiniciais/tela-inicial";
	}
	
	@GetMapping("/ecolher_pagina_inicio/{id}")
	public String escolherPagina(@PathVariable Integer id) {
		
		pagina = id;
		
		return "redirect:/inicio";
	}
	
	@GetMapping("/buscar_categoria")
	public String buscarPorCategoria(@RequestParam("categorias") List<String> categorias) {
		
		for(int i = 0; i < categorias.size(); i++) {
			System.out.println("Categoria: "+categorias.get(i));
		}
		
//		System.out.println("Categoria: "+categoria);
		return "redirect:/inicio";
	}
}

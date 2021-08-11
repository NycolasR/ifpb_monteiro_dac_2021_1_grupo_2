package com.bookstore.controller;

import java.util.LinkedHashSet;
import java.util.Set;

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
import com.bookstore.facades.FacadeCategoria;
import com.bookstore.facades.FacadeEditoras;
import com.bookstore.facades.FacadeLivros;
import com.bookstore.model.Autor;
import com.bookstore.model.Categoria;
import com.bookstore.model.Editora;
import com.bookstore.model.Livro;

@Controller
public class ControllerCrudLivros {

	@Autowired
	private FacadeLivros facadeLivros;

	@Autowired
	private FacadeAutor facadeAutor;
	
	@Autowired
	private FacadeCategoria facadeCategoria;
	
	@Autowired
	private FacadeEditoras facadeEditoras;
	
	private Set<Autor> autoresParaSalvar = new LinkedHashSet<Autor>();
	private Set<Categoria> categoriasParaSalvar = new LinkedHashSet<Categoria>();

	private Livro livroListas;
	private Long idInformado;
	private String excecao = "";
	private Integer pagina = 1;

	/**
	 * Esse método faz a listagem dos métodos para serem mostrados na página html
	 * @param model
	 * @return
	 */
	@GetMapping("/livro")
	public String recuperarLivros(Model model) {

		try {
			
			Page<Livro> livros = facadeLivros.paginarLivros("titulo", Direction.ASC, pagina, false, null, "");
			
			model.addAttribute("livros", livros);
			model.addAttribute("numeracao", facadeLivros.criarPaginacao(livros.getTotalPages(), pagina));
			model.addAttribute("fim", livros.getTotalPages());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "livros/livros";
	}

	@GetMapping("/livroform/{id}")
	public String recuperarFormulario(@PathVariable("id") Long id, Model model) {

		Livro livro = null;

		try {

			//caso seja -1 significa q foi atualizado o livro sem ser adicionado ao banco de dados
			if (id == -1) {
				
				livro = livroListas;

			} else if (id > 0) {// os ids cadastrados no banco são maiores que 0

				autoresParaSalvar = null;
				livro = facadeLivros.recuperarLivro(id);
				livroListas = livro;
				idInformado = id;
				excecao = "";
				
				
			} else {
				
				autoresParaSalvar = null;
				livro = facadeLivros.recuperarLivroNulo();
				livroListas = livro;
				idInformado = id;
				excecao = "";
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			autoresParaSalvar = null;
			livro = facadeLivros.recuperarLivroNulo();
			livroListas = livro;
			idInformado = 0L;
			excecao = e.getMessage();
			
		}
		
		autoresParaSalvar = livro.getAutores();
		categoriasParaSalvar = livro.getCategorias();
		
		model.addAttribute("livro", livro);
		model.addAttribute("autores", facadeAutor.recuperarAutores());
		model.addAttribute("autor",facadeAutor.recuperarAutorNulo());
		model.addAttribute("categorias", facadeCategoria.recuperarCategorias());
		model.addAttribute("categoria", facadeCategoria.recuperarCategoriaNula());
		model.addAttribute("editoras", facadeEditoras.recuperarEditoras());
		model.addAttribute("editora", facadeEditoras.recuperarEditoraNula());
		model.addAttribute("isAdicionar", idInformado);
		model.addAttribute("excecao",excecao);

		return "livros/livrosform";
	}

	//Esse método atualiza a lista de autores do livro  sem salvar o livro no banco
	@PostMapping("/livroformupdateautores/{id}")
	public String atualizarListaAutores(@ModelAttribute Livro livro, @PathVariable("id") Long id) {
		
		try {

			autoresParaSalvar.add(facadeAutor.recuperarAutor(id));
			livro.setAutores(autoresParaSalvar);
			livro.setCategorias(categoriasParaSalvar);
			livroListas = livro;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return "redirect:/livroform/-1";

	}
	
	//esse método cria um autor no banco
	@PostMapping("/livroformautoradd")
	public String criarAutor(@ModelAttribute Autor autor) {
		
		try {
			facadeAutor.criarAutor(autor).getID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
	//Esse método atualiza a lista de categorias do livro  sem salvar o livro no banco
	@PostMapping("/livroformupdatecategorias/{id}")
	public String atualizarListaCategorias(@ModelAttribute Livro livro, @PathVariable("id") Long id) {
		
		try {
			
			categoriasParaSalvar.add(facadeCategoria.recuperarCategoria(id));
			livro.setCategorias(categoriasParaSalvar);
			livro.setAutores(autoresParaSalvar);
			livroListas = livro;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
	//esse método cria uma categoria nova no banco
	@PostMapping("/livroformcategoriaadd")
	public String criarCategoria(@ModelAttribute Categoria categoria) {
		
		try {
			facadeCategoria.criarCategoria(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
	//esse método deleta uma categoria do banco através do id
	@PostMapping("/livroformcategoriaremove/{id}")
	public String deletarCategoria(@PathVariable("id") Long id) {
		
		try {
			facadeCategoria.removerCategoria(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
	//esse método cria uma nova Editora no banco
	@PostMapping("/livroformeditoraadd")
	public String criarEditora(@ModelAttribute Editora editora) {
		
		try {
			facadeEditoras.criarEditora(editora);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return"redirect:/livroform/-1";
	}
	
	/**
	 * esse método cria um novo livro no banco, no entanto recomenda-se criar primeiramente, a editora, os autores e as categorias a serem adicionadas
	 * ao livro, caso não existam, visando a não anulagem dos campos já preenchidos.
	 * 
	 * O preço, recomenda-se inseri-lo como valor real, ou seja, com ponto flutuante
	 * 
	 */
	@PostMapping("/livroformadd")
	public String criarLivro(@Valid @ModelAttribute Livro livro, BindingResult result, Model model) {
				
		try {
			
			livro.setEditora(facadeEditoras.recuperarEditora(livro.getEditora().getId()));
			livro.setAutores(autoresParaSalvar);
			livro.setCategorias(categoriasParaSalvar);
			
			if (result.hasErrors()) {
				model.addAttribute("autores", facadeAutor.recuperarAutores());
				model.addAttribute("autor",facadeAutor.recuperarAutorNulo());
				model.addAttribute("categorias", facadeCategoria.recuperarCategorias());
				model.addAttribute("categoria", facadeCategoria.recuperarCategoriaNula());
				model.addAttribute("editoras", facadeEditoras.recuperarEditoras());
				model.addAttribute("editora", facadeEditoras.recuperarEditoraNula());
				model.addAttribute("isAdicionar", 0);
				model.addAttribute("excecao",excecao);
				return "livros/livrosform";
			}
			
			Long id = facadeLivros.criarLivro(livro);
			
			facadeAutor.salvarLivroAosAutores(autoresParaSalvar, facadeLivros.recuperarLivro(id));
			facadeCategoria.salvarLivroAsCategorias(categoriasParaSalvar, facadeLivros.recuperarLivro(id));
			
		} catch (Exception e) {
			e.printStackTrace();
			livroListas = livro;
			excecao = e.getMessage();
			return"redirect:/livroform/-1";
		}
		
		return "redirect:/livro";
	}
	
	/**
	 * 
	 * Esse método atualiza as informações de um livro presente no banco de dados
	 * 
	 */
	@PostMapping("/livroformupdate")
	public String atualizarLivro(@Valid @ModelAttribute Livro livro, BindingResult result, Model model) {
		
		livro.setAutores(autoresParaSalvar);
		livro.setCategorias(categoriasParaSalvar);
		
		if (result.hasErrors()) {
			model.addAttribute("autores", facadeAutor.recuperarAutores());
			model.addAttribute("autor",facadeAutor.recuperarAutorNulo());
			model.addAttribute("categorias", facadeCategoria.recuperarCategorias());
			model.addAttribute("categoria", facadeCategoria.recuperarCategoriaNula());
			model.addAttribute("editoras", facadeEditoras.recuperarEditoras());
			model.addAttribute("editora", facadeEditoras.recuperarEditoraNula());
			model.addAttribute("excecao",excecao);
			return "livros/livrosform";
		}
		
		try {
			
			facadeLivros.atualizarLivro(livro, idInformado);
			
			//depois de criar ou atualizar é necessário setar o livro aos autores e as categorias correspondentes
			facadeAutor.salvarLivroAosAutores(autoresParaSalvar, facadeLivros.recuperarLivro(idInformado));
			facadeCategoria.salvarLivroAsCategorias(categoriasParaSalvar, facadeLivros.recuperarLivro(idInformado));
			
		} catch (Exception e) {
			e.printStackTrace();
			livroListas = livro;
			excecao = e.getMessage();
			return"redirect:/livroform/-1";
		}
		
		
		return "redirect:/livro";
	}
	
	/**
	 * 
	 * Esse método faz a remoção de um livro do banco de dados caso ele não esteja presente em um Carrinho de compras
	 */
	@PostMapping("/livroformremove")
	public String removerLivro(Model model) {
		
		try {
			facadeLivros.deletarLivro(idInformado);
		} catch (Exception e) {
			e.printStackTrace();
			excecao = e.getMessage();
			return"redirect:/livroform/-1";
		}
		
		return "redirect:/livro";
	}
	
	@GetMapping("/voltar_livro")
	public String voltarParaTelaLivros() {
		
		return "redirect:/livro";
	}
	
	@GetMapping("/ecolher_pagina_livro/{id}")
	public String escolherPagina(@PathVariable Integer id) {
		
		pagina = id;
		
		return "redirect:/livro";
	}

}

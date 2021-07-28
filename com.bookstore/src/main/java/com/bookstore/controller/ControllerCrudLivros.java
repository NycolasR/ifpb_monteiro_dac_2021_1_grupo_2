package com.bookstore.controller;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.imageio.stream.FileImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/livro")
	public String recuperarLivros(Model model) {

		model.addAttribute("livros", facadeLivros.recuperarLivros());

		return "livros/livros";
	}

	@GetMapping("/livroform/{id}")
	public String recuperarFormulario(@PathVariable("id") Long id, Model model) {

		Livro livro = null;

		try {

			if (id == -1) {
				
				livro = livroListas;

			} else if (id > 0) {// os ids cadastrados no banco são maiores que 0

				autoresParaSalvar = null;
				livro = facadeLivros.recuperarLivro(id);
				livroListas = livro;
				idInformado = id;
				
			} else {
				
				autoresParaSalvar = null;
				livro = facadeLivros.recuperarLivroNulo();
				livroListas = livro;
				idInformado = id;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			autoresParaSalvar = null;
			livro = facadeLivros.recuperarLivroNulo();
			livroListas = livro;
			idInformado = id;
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

		return "livros/livrosform";
	}

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
	
	@PostMapping("/livroformautoradd")
	public String criarAutor(@ModelAttribute Autor autor) {
		
		try {
			facadeAutor.criarAutor(autor).getID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
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
	
	@PostMapping("/livroformcategoriaadd")
	public String criarCategoria(@ModelAttribute Categoria categoria) {
		
		try {
			facadeCategoria.criarCategoria(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
	@PostMapping("/livroformcategoriaremove/{id}")
	public String deletarCategoria(@PathVariable("id") Long id) {
		
		try {
			facadeCategoria.removerCategoria(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livroform/-1";
	}
	
	@PostMapping("/livroformeditoraadd")
	public String criarEditora(@ModelAttribute Editora editora) {
		
		try {
			facadeEditoras.criarEditora(editora);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return"redirect:/livroform/-1";
	}
	
	@PostMapping("/livroformadd")
	public String criarLivro(@ModelAttribute Livro livro) {
				
		try {
			
			livro.setEditora(facadeEditoras.recuperarEditora(livro.getEditora().getId()));
			livro.setAutores(autoresParaSalvar);
			livro.setCategorias(categoriasParaSalvar);
			
			Long id = facadeLivros.criarLivro(livro);
			
			facadeAutor.salvarLivroAosAutores(autoresParaSalvar, facadeLivros.recuperarLivro(id));
			facadeCategoria.salvarLivroAsCategorias(categoriasParaSalvar, facadeLivros.recuperarLivro(id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livro";
	}
	
	@PostMapping("/livroformupdate")
	public String atualizarLivro(@ModelAttribute Livro livro) {
		
		livro.setAutores(autoresParaSalvar);
		livro.setCategorias(categoriasParaSalvar);
		
		try {
			facadeLivros.atualizarLivro(livro, idInformado);
			facadeAutor.salvarLivroAosAutores(autoresParaSalvar, facadeLivros.recuperarLivro(idInformado));
			facadeCategoria.salvarLivroAsCategorias(categoriasParaSalvar, facadeLivros.recuperarLivro(idInformado));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/livro";
	}
	
	@PostMapping("/livroformremove")
	public String removerLivro() {
		
		try {
			facadeLivros.deletarLivro(idInformado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/livro";
	}

}

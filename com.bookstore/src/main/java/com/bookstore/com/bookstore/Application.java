package com.bookstore.com.bookstore;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.model.Estoque;
import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.service.EditoraService;
import com.bookstore.com.bookstore.service.EstoqueService;
import com.bookstore.com.bookstore.service.LivroService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private EstoqueService estoqueService;
	private LivroService livroService;
	private EditoraService editoraService;
	
	private Livro livro1;
	private Livro livro2;
	private Livro livro3;
	private Livro livro4;
	private Livro livro5;
	private Livro livro6;
	
	public Application(
			EstoqueService estoqueService,
			LivroService livroService,
			EditoraService editoraService) {
		
		this.estoqueService = estoqueService;
		this.livroService = livroService;
		this.editoraService = editoraService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		livro1 = new Livro(111l, "Titulo 1", "Descrição 1", 10f, 1, LocalDate.of(2015, 12, 1));
//		livro2 = new Livro(222l, "Titulo 2", "Descrição 2", 20f, 2, LocalDate.of(2015, 12, 2));
//		livro3 = new Livro(333l, "Titulo 3", "Descrição 3", 30f, 3, LocalDate.of(2015, 12, 3));
//		livro4 = new Livro(444l, "Titulo 4", "Descrição 4", 40f, 4, LocalDate.of(2015, 12, 4));
//		livro5 = new Livro(555l, "Titulo 5", "Descrição 5", 50f, 5, LocalDate.of(2015, 12, 5));
//		livro6 = new Livro(666l, "Titulo 6", "Descrição 6", 60f, 6, LocalDate.of(2015, 12, 6));
//		
//		Editora editora1 = new Editora("Editora 1", "Cidade 1");
//		editora1.addLivro(livro1);
//		editora1.addLivro(livro2);
//		editora1.addLivro(livro3);
//		
//		Editora editora2 = new Editora("Editora 2", "Cidade 2");
//		editora2.addLivro(livro4);
//		editora2.addLivro(livro5);
//		editora2.addLivro(livro6);
//		
//		editoraService.salvar(editora1);
//		editoraService.salvar(editora2);
		
//		testarEstoque();
		
		System.out.println("Deu certo");
	}

	private void testarEstoque() {
		Estoque estoque = new Estoque();
		estoque.adicionarLivro(livroService.recuperarPeloISBN(111l));
		estoque.adicionarLivro(livroService.recuperarPeloISBN(222l));
		estoque.adicionarLivro(livroService.recuperarPeloISBN(333l));
		estoque.adicionarLivro(livroService.recuperarPeloISBN(444l));

		try {
			estoque.excluirLivro(livroService.recuperarPeloISBN(111l));
			estoque.excluirLivro(livroService.recuperarPeloISBN(222l));			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		estoqueService.salvar(estoque);
	}
}

package com.bookstore.com.bookstore;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.com.bookstore.model.Categoria;
import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.model.RegistroVendas;
import com.bookstore.com.bookstore.model.Usuario;

import com.bookstore.com.bookstore.service.EditoraService;
import com.bookstore.com.bookstore.service.LivroService;
import com.bookstore.com.bookstore.service.RegistroVendasService;
import com.bookstore.com.bookstore.service.UsuarioService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private EditoraService editoraService;
	private UsuarioService usuarioService;
	private RegistroVendasService registroVendasService;
	private LivroService livroService;
	
	private Livro livro1, livro2, livro3, livro4, livro5, livro6;
	private Usuario cliente1, cliente2, cliente3;
	
	public Application(
			EditoraService editoraService,
			UsuarioService usuarioService,
			RegistroVendasService registroVendasService,
			LivroService livroService) {
		
		this.livroService = livroService;
		this.editoraService = editoraService;
		this.usuarioService = usuarioService;
		this.registroVendasService = registroVendasService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		main_nycolas();
		
		main_app();
	}

	private void main_app() {
		boolean flag = true;

		Scanner input = new Scanner(System.in);
		
		//Menu
		while(flag) {
			
			System.out.print(
					"\n0 - Sair"
					+"\n1 - Registrar novo usuário"
					+"\n2 - Consultar usuário pelo e-mail"
					+"\n3 - Cadastrar e alterar autor"
					+"\n4 - Cadastrar, alterar e excluir livro"
					+"\n5 - Cadastrar um livro do catálogo ao estoque"
					+"\n6 - Consultar os 5 livros mais baratos disponíveis no estoque"
					+"\n7 - Consultar todos os livros ordenados de forma ascendente pelo título de forma paginada"
					+"\n8 - Adicionar um livro a um pedido (carrinho de compras)"
					+ "\nOpção: ");
			
			int opcao = Integer.parseInt(input.nextLine());
			
			switch(opcao) {

			case 1: 
				
				break;

			case 2:

				break;

			case 3:

				break;

			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;

			default:
				System.out.println("\n\n << EXECUÇÃO DO PROGRAMA FINALIZADA >>");
				flag = false;
			}
		}

		input.close();
	}

	private void main_nycolas() {
		
		livro1 = new Livro(111l, "Titulo 1", "Descrição 1", new BigDecimal("10"), 1, LocalDate.of(2015, 12, 1), 10);
		livro2 = new Livro(222l, "Titulo 2", "Descrição 2", new BigDecimal("20"), 2, LocalDate.of(2015, 12, 2), 20);
		livro3 = new Livro(333l, "Titulo 3", "Descrição 3", new BigDecimal("30"), 3, LocalDate.of(2015, 12, 3), 30);
		livro4 = new Livro(444l, "Titulo 4", "Descrição 4", new BigDecimal("40"), 4, LocalDate.of(2015, 12, 4), 40);
		livro5 = new Livro(555l, "Titulo 5", "Descrição 5", new BigDecimal("50"), 5, LocalDate.of(2015, 12, 5), 50);
		livro6 = new Livro(666l, "Titulo 6", "Descrição 6", new BigDecimal("60"), 6, LocalDate.of(2015, 12, 6), 60);
		
		livro1.addCategoria(Categoria.AVENTURA);
		livro1.addCategoria(Categoria.AVENTURA);
		livro1.addCategoria(Categoria.FICCAO_CIENTIFICA);
		
		livro2.addCategoria(Categoria.BIOGRAFIA);
		livro2.addCategoria(Categoria.CLASSICO);
		livro2.addCategoria(Categoria.ENGENHARIA);
		
		livro1.setImageFile(new File("C:\\Users\\Nyk\\git\\ifpb_monteiro_dac_2021_1_grupo_2\\com.bookstore\\leao.jpg"));
		
		Editora editora1 = new Editora("Editora 1", "Cidade 1");
		editora1.addLivro(livro1);
		editora1.addLivro(livro2);
		editora1.addLivro(livro3);
		
		Editora editora2 = new Editora("Editora 2", "Cidade 2");
		editora2.addLivro(livro4);
		editora2.addLivro(livro5);
		editora2.addLivro(livro6);
		
		editoraService.salvar(editora1);
		editoraService.salvar(editora2);
		
		criarClientes();
		
		testarRegistroVendas();
		
		System.out.println("Deu certo");
		
	}

	private void criarClientes() {
		cliente1 = new Usuario();
		cliente1.setNome("Cliente 1");
		cliente1.setEmail("cliente1@email.com");
		cliente1.setSenha("senha1");
		cliente1.setAdmin(false);
		
		cliente2 = new Usuario();
		cliente2.setNome("Cliente 2");
		cliente2.setEmail("cliente2@email.com");
		cliente2.setSenha("senha2");
		cliente2.setAdmin(false);
		
		cliente3 = new Usuario();
		cliente3.setNome("Cliente 3");
		cliente3.setEmail("cliente3@email.com");
		cliente3.setSenha("senha3");
		cliente3.setAdmin(false);
		
		usuarioService.salvar(cliente1);
		usuarioService.salvar(cliente2);
		usuarioService.salvar(cliente3);
	}

	private void testarRegistroVendas() {
		RegistroVendas registro1 = new RegistroVendas(LocalDate.now(), livro1, cliente1, 15, new BigDecimal("15.5"));
		RegistroVendas registro2 = new RegistroVendas(LocalDate.now(), livro2, cliente2, 15, new BigDecimal("30.5"));
		RegistroVendas registro3 = new RegistroVendas(LocalDate.now(), livro3, cliente3, 15, new BigDecimal("45.5"));
		
		registroVendasService.salvar(registro1);
		registroVendasService.salvar(registro2);
		registroVendasService.salvar(registro3);
		
	}
}














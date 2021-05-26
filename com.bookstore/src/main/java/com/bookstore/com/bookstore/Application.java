package com.bookstore.com.bookstore;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Scanner;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.com.bookstore.model.Categoria;
import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.model.Endereco;
import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.model.RegistroVendas;
import com.bookstore.com.bookstore.model.Usuario;

import com.bookstore.com.bookstore.service.EditoraService;
import com.bookstore.com.bookstore.service.LivroService;
import com.bookstore.com.bookstore.service.EnderecoService;
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

	private Usuario cliente4;
	private Usuario cliente5;
	private Usuario cliente6;
	
	private Usuario clienteTeste;
	
	private Endereco endereco1;
	private Endereco endereco2;
	private Endereco endereco3;
	
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
		//main_Pedro();
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
	
	private void main_Pedro() {
		
		usuarioService.excluirTudo();
		
		criarClientes();
		criarClientesComEndereco();
		excluirClientes();
		
		List<Usuario> userResult = usuarioService.usuarioPorEmail("cliente5@email.com");
		for(Usuario u : userResult) {
			List<Endereco> enderecos = u.getEnderecos();
			System.out.println(u.getNome());
			System.out.println(enderecos.get(0).toString());
		}

		
		
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
		
//		livro1.setImageFile(new File("C:\\Users\\Nyk\\git\\ifpb_monteiro_dac_2021_1_grupo_2\\com.bookstore\\leao.jpg"));
		
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
		
		try {
			usuarioService.salvar(cliente1);
			usuarioService.salvar(cliente2);
			usuarioService.salvar(cliente3);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		
//		Teste da verificação de email duplicados
		
//		clienteTeste = new Usuario();
//		clienteTeste.setNome("Cliente Teste");
//		clienteTeste.setEmail("clienteTeste@email.com");
//		clienteTeste.setSenha("senhaTeste");
//		clienteTeste.setAdmin(false);
//		
//		try {
//			usuarioService.salvar(clienteTeste);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());;
//		}
		
	}

	private void criarClientesComEndereco() {
		
		endereco1 = new Endereco();
		endereco1.setBairro("bairro 1");
		endereco1.setCEP(11111111);
		endereco1.setCidade("cidade 1");
		endereco1.setComplemento("comp 1");
		endereco1.setNumero(001);
		endereco1.setRua("Rua 1");
		endereco1.setUF("PB");;
		
		endereco2 = new Endereco();
		endereco2.setBairro("bairro 2");
		endereco2.setCEP(22222222);
		endereco2.setCidade("cidade 2");
		endereco2.setComplemento("comp 2");
		endereco2.setNumero(002);
		endereco2.setRua("Rua 2");
		endereco2.setUF("PB");;
		
		endereco3 = new Endereco();
		endereco3.setBairro("bairro 3");
		endereco3.setCEP(33333333);
		endereco3.setCidade("cidade 3");
		endereco3.setComplemento("comp 3");
		endereco3.setNumero(003);
		endereco3.setRua("Rua 3");
		endereco3.setUF("PB");;
		
		
		cliente4 = new Usuario();
		cliente4.setNome("Cliente 4");
		cliente4.setEmail("cliente4@email.com");
		cliente4.addEndereco(endereco1);
		cliente4.setSenha("senha4");
		cliente4.setAdmin(false);
		
		cliente5 = new Usuario();
		cliente5.setNome("Cliente 5");
		cliente5.setEmail("cliente5@email.com");
		cliente5.addEndereco(endereco2);
		cliente5.setSenha("senha5");
		cliente5.setAdmin(false);
		
		cliente6 = new Usuario();
		cliente6.setNome("Cliente 6");
		cliente6.setEmail("cliente6@email.com");
		cliente6.addEndereco(endereco3);
		cliente6.setSenha("senha6");
		cliente6.setAdmin(false);
		
		try {
		usuarioService.salvar(cliente4);
		usuarioService.salvar(cliente5);
		usuarioService.salvar(cliente6);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		
	}
	
	private void excluirClientes() {
		
		Usuario clienteExc = usuarioService.usuarioPorEmail("cliente4@email.com").get(0);
		
		usuarioService.excluir(clienteExc);
		
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














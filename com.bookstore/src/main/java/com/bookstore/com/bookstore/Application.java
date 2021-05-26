package com.bookstore.com.bookstore;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Scanner;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

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
	private Livro livro7, livro8, livro9, livro10, livro11, livro12;
	
	private Usuario cliente1, cliente2, cliente3, cliente4, cliente5, cliente6;
	
	private Usuario clienteTeste;
	
	private Endereco endereco1, endereco2, endereco3;
	
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
		main_nycolas();
//		main_Pedro();
//		main_app();
	}

	private void main_app() {
		boolean flag = true;

		Scanner input = new Scanner(System.in);

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
		
		livro1 = new Livro(111l, "Titulo 1", "Descrição 1", new BigDecimal("10"), 1, 2015, 10);
		livro2 = new Livro(222l, "Titulo 2", "Descrição 2", new BigDecimal("20"), 2, 2015, 20);
		livro3 = new Livro(333l, "Titulo 3", "Descrição 3", new BigDecimal("30"), 3, 2015, 30);
		livro4 = new Livro(444l, "Titulo 4", "Descrição 4", new BigDecimal("40"), 4, 2015, 40);
		livro5 = new Livro(555l, "Titulo 5", "Descrição 5", new BigDecimal("50"), 5, 2015, 50);
		livro6 = new Livro(666l, "Titulo 6", "Descrição 6", new BigDecimal("60"), 6, 2015, 60);
		
		livro7 = new Livro(777l, "Titulo 7", "Descrição 7", new BigDecimal("70"), 7, 2015, 70);
		livro8 = new Livro(888l, "Titulo 8", "Descrição 8", new BigDecimal("80"), 8, 2015, 80);
		livro9 = new Livro(999l, "Titulo 9", "Descrição 9", new BigDecimal("90"), 9, 2015, 90);
		livro10 = new Livro(101l, "Titulo 10", "Descrição 10", new BigDecimal("100"), 10, 2015, 100);
		livro11 = new Livro(111l, "Titulo 11", "Descrição 11", new BigDecimal("110"), 11, 2015, 110);
		livro12 = new Livro(121l, "Titulo 12", "Descrição 12", new BigDecimal("120"), 12, 2015, 120);
		
//		livro1.addCategoria(Categoria.AVENTURA);
//		livro1.addCategoria(Categoria.FICCAO_CIENTIFICA);
//		livro2.addCategoria(Categoria.BIOGRAFIA);
//		livro2.addCategoria(Categoria.CLASSICO);
//		livro2.addCategoria(Categoria.ENGENHARIA);
		
//		livro1.setImageFile(new File("C:\\Users\\Nyk\\git\\ifpb_monteiro_dac_2021_1_grupo_2\\com.bookstore\\leao.jpg"));
		
		
		
		Editora editora1 = new Editora("Editora 1", "Cidade 1");
		editora1.addLivro(livro1);
		editora1.addLivro(livro2);
		editora1.addLivro(livro3);		
		editora1.addLivro(livro4);
		editora1.addLivro(livro5);
		editora1.addLivro(livro6);
		
		Editora editora2 = new Editora("Editora 2", "Cidade 2");
		editora2.addLivro(livro7);
		editora2.addLivro(livro8);
		editora2.addLivro(livro9);		
		editora2.addLivro(livro10);
		editora2.addLivro(livro11);
		editora2.addLivro(livro12);
		
//		livroService.salvarLivro(livro1);
//		livroService.salvarLivro(livro2);
//		livroService.salvarLivro(livro3);
//		livroService.salvarLivro(livro4);
//		livroService.salvarLivro(livro5);
//		livroService.salvarLivro(livro6);
//		livroService.salvarLivro(livro7);
//		livroService.salvarLivro(livro8);
//		livroService.salvarLivro(livro9);
//		livroService.salvarLivro(livro10);
//		livroService.salvarLivro(livro11);
//		livroService.salvarLivro(livro12);
//		editoraService.salvarEditora(editora1);
//		editoraService.salvarEditora(editora2);
		
		Optional<Editora> optional = editoraService.recuperarPeloId(4l);
		if(optional.isPresent()) {
			System.out.println(optional.get());
			
			optional.get().setCidade("Cidade Atualizada");
			optional.get().setNome("Nome atualizado");
			editoraService.atualizarEditora(optional.get());
			
			System.out.println();
			System.out.println(optional.get());
		}
		
//		criarClientes();
//		testarRegistroVendas();
		
		System.err.println("\nDeu certo\n");
		
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














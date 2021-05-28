package com.bookstore.com.bookstore;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.service.AutorService;
import com.bookstore.com.bookstore.service.EditoraService;
import com.bookstore.com.bookstore.service.FormaPagamentoService;
import com.bookstore.com.bookstore.service.ItemPedidoService;
import com.bookstore.com.bookstore.service.LivroService;
import com.bookstore.com.bookstore.service.PedidoService;
import com.bookstore.com.bookstore.service.RegistroVendasService;
import com.bookstore.com.bookstore.service.UsuarioService;

@SpringBootApplication
public class BookStoreApp implements CommandLineRunner {

	private EditoraService editoraService;
	private UsuarioService usuarioService;
	private RegistroVendasService registroVendasService;
	private LivroService livroService;
	private ItemPedidoService itemPedidoService;
	private FormaPagamentoService formaPagamentoService;
	private AutorService autorService;
	private PedidoService pedidoService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApp.class, args);
	}
	
	public BookStoreApp(
			EditoraService editoraService,
			UsuarioService usuarioService,
			RegistroVendasService registroVendasService,
			LivroService livroService,
			ItemPedidoService itemPedidoService,
			FormaPagamentoService formaPagamentoService,
			AutorService autorService,
			PedidoService pedidoService) {
		
		this.livroService = livroService;
		this.editoraService = editoraService;
		this.usuarioService = usuarioService;
		this.registroVendasService = registroVendasService;
		this.itemPedidoService = itemPedidoService;
		this.formaPagamentoService = formaPagamentoService;
		this.autorService = autorService;
		this.pedidoService = pedidoService;
		
	}
	
	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		boolean flag = true;

		while(flag) {

			System.out.print(
					"\n0 - Sair"
					+"\n1 - Registrar novo usuário"
					+"\n2 - Consultar usuário pelo e-mail"
					+"\n3 - Cadastrar Autor"
					+"\n4 - Alterar Autor"
					+"\n5 - Cadastrar Livro"
					+"\n6 - Alterar Livro"
					+"\n7 - Excluir Livro"
					+"\n8 - Cadastrar um livro do catálogo ao estoque"
					+"\n9 - Consultar os 5 livros mais baratos disponíveis no estoque"
					+"\n10 - Consultar todos os livros ordenados de forma ascendente pelo título de forma paginada"
					+"\n11 - Adicionar um livro a um pedido (carrinho de compras)"
					+"\nOpção: ");

			int opcao = Integer.parseInt(scanner.nextLine());

			switch(opcao) {

			case 1: // Pedro

				break;

			case 2: // Pedro

				break;

			case 3: // Gabriel

				break;

			case 4: // Gabriel

				break;

			case 5: // Nycolas - Cadastrar Livro

				break;

			case 6: // Nycolas - Alterar Livro
				Livro livroEditado = null;
				
				try {
					Long idEditado = selecionarLivro();
					livroEditado = recuperarLivro(idEditado);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				System.out.print("Novo título (pressione enter p/ ignorar): ");
				
				String resposta = scanner.nextLine();
				
				if(!resposta.isEmpty())
					System.out.println("Novo título: " + resposta);
				
//				Integer anoPublicacao;
//				String descricao;
//				Integer edicao;
//				Long isbn;
//				BigDecimal preco;
//				Integer qntdEstoque;

				break;

			case 7: // Nycolas - Excluir Livro

				try {
					Long idExcluido = selecionarLivro();
					livroService.deletarPeloId(idExcluido);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;

			case 8: // Nycolas - Cadastrar um livro do catálogo ao estoque

				break;

			case 9: // Pedro

				break;

			case 10: // Pedro

				break;

			case 11: // Gabriel

				break;


			default:
				System.err.println("\n\n<< EXECUÇÃO DO PROGRAMA FINALIZADA >>\n\n");
				flag = false;
			}
		}
	}

	private Livro recuperarLivro(Long idEditado) throws Exception {
		Optional<Livro> optional = livroService.recuperarPeloId(idEditado);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("[ERRO] Livro não encontrado na base de dados.");
	}

	private Long selecionarLivro() throws Exception {
		
		if(livroService.existemRegistros()) {
			Scanner input = new Scanner(System.in);
			
			List<Livro> livros = livroService.listarLivros();
			
			System.out.println("Livros Cadastrados:\n");
			for (Livro livro : livros) {
				System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo());
			}
			System.out.print("Informe o ID do livro selecionado: ");
			Long id = Long.parseLong(input.nextLine());
			
			return id;
		}
		
		throw new Exception("[ERRO] Não existem livros cadastrados no sistema.");
	}

}

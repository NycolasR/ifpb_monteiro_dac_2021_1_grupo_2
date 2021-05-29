package com.bookstore.com.bookstore;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.com.bookstore.facades.FacadeEditoras;
import com.bookstore.com.bookstore.facades.FacadeLivros;
import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.service.AutorService;
import com.bookstore.com.bookstore.service.FormaPagamentoService;
import com.bookstore.com.bookstore.service.ItemPedidoService;
import com.bookstore.com.bookstore.service.PedidoService;
import com.bookstore.com.bookstore.service.RegistroVendasService;
import com.bookstore.com.bookstore.service.UsuarioService;

@SpringBootApplication
public class BookStoreApp implements CommandLineRunner {

	private UsuarioService usuarioService;
	private RegistroVendasService registroVendasService;
	private ItemPedidoService itemPedidoService;
	private FormaPagamentoService formaPagamentoService;
	private AutorService autorService;
	private PedidoService pedidoService;
	
	private FacadeLivros facadeLivros;
	private FacadeEditoras facadeEditoras;
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApp.class, args);
	}
	
	public BookStoreApp(
			UsuarioService usuarioService,
			RegistroVendasService registroVendasService,
			ItemPedidoService itemPedidoService,
			FormaPagamentoService formaPagamentoService,
			AutorService autorService,
			PedidoService pedidoService,
			FacadeLivros facadeLivros,
			FacadeEditoras facadeEditoras) {
		
		this.usuarioService = usuarioService;
		this.registroVendasService = registroVendasService;
		this.itemPedidoService = itemPedidoService;
		this.formaPagamentoService = formaPagamentoService;
		this.autorService = autorService;
		this.pedidoService = pedidoService;
		
		this.facadeLivros = facadeLivros;
		this.facadeEditoras = facadeEditoras;
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
					+"\n12 - Ler dados de 1 livro"
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
				
				// Ler dados do livro
				System.out.print("Informe o título do livro: ");
				String titulo = scanner.nextLine();
				
				System.out.print("Informe o ISBN do livro: ");
				Long isbn = Long.parseLong(scanner.nextLine());
				
				System.out.print("Informe a descrição do livro: ");
				String descricao = scanner.nextLine();
				
				System.out.print("Informe o preço do livro: ");
				BigDecimal preco = null;
				while(true) {
					try {
						preco = new BigDecimal(scanner.nextLine());
						break;
					} catch(NumberFormatException e) {
						System.err.print("[ERRO] Insira um formato de preço válido: ");
					}
				}
				
				System.out.print("Informe a edição do livro: ");
				Integer edicao = Integer.parseInt(scanner.nextLine());
				
				System.out.print("Informe o ano de publicação do livro: ");
				Integer anoPublicacao = Integer.parseInt(scanner.nextLine());
				
				System.out.print("Informe a quantidade de unidades do livro para estoque: ");
				Integer qntdEstoque = Integer.parseInt(scanner.nextLine());
				
				// Adicionar categorias ao livro
				
				// Listar editoras
				// [Se o user quiser cria-se uma nova]
				Editora editoraSelecionada = null;

				System.out.println("\nAgora, selecione a editora deste livro.");
				try {
					editoraSelecionada = facadeEditoras.selecionarEditora();
				} catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				
				// Listar Autores
				// [Se o usuário quiser cria-se um novo]
				
				// Seta tudo um no outro e salva
				Long idLivro = facadeLivros.salvarLivro(isbn, titulo, descricao, preco, edicao, anoPublicacao, qntdEstoque);
				editoraSelecionada.addLivro(facadeLivros.recuperarLivro(idLivro));
				facadeEditoras.atualizarEditora(editoraSelecionada);
				
				// Mostra os dados salvos pela console

				System.err.println(editoraSelecionada);
				System.err.println(facadeLivros.recuperarLivro(idLivro));
				
				break;

			case 6: // Nycolas - Alterar Livro
				Livro livroEditado = null;
				
				try {
					livroEditado = facadeLivros.selecionarLivro();
					System.err.println(livroEditado);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				System.out.print("\nNovo título (pressione enter p/ ignorar): ");
				facadeLivros.atualizarAtributoLivro(livroEditado, "titulo");
				
				System.out.print("Novo ISBN (pressione enter p/ ignorar): ");
				facadeLivros.atualizarAtributoLivro(livroEditado, "ISBN");
				
				System.out.print("Nova descrição (pressione enter p/ ignorar): ");
				facadeLivros.atualizarAtributoLivro(livroEditado, "descricao");
				
				System.out.print("Novo preço R$ (pressione enter p/ ignorar): ");
				facadeLivros.atualizarAtributoLivro(livroEditado, "preco");
				
				System.out.print("Nova edição (pressione enter p/ ignorar): ");
				facadeLivros.atualizarAtributoLivro(livroEditado, "edicao");
				
				System.out.print("Novo ano de publicação (pressione enter p/ ignorar): ");
				facadeLivros.atualizarAtributoLivro(livroEditado, "anoPublicacao");

				System.out.println("\nDados atualizados:");
				System.err.println(livroEditado);
				facadeLivros.atualizarLivro(livroEditado);
				break;

			case 7: // Nycolas - Excluir Livro

				try {
					facadeLivros.deletarLivro(facadeLivros.selecionarLivro());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;

			case 8: // Nycolas - Cadastrar um livro do catálogo ao estoque
				
				Livro livoSelecionado = null;
				
				try {
					livoSelecionado = facadeLivros.selecionarLivro();
					System.err.println(livoSelecionado);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
				System.out.print("Informe a quantidade de unidades que deseja cadastrar: ");
				facadeLivros.atualizarAtributoLivro(livoSelecionado, "quantidadeEmEstoque");
				facadeLivros.atualizarLivro(livoSelecionado);
				
				break;

			case 9: // Pedro

				break;

			case 10: // Pedro

				break;

			case 11: // Gabriel

				break;

			case 12: // Nycolas - Ler dados de 1 livro
				
				try {
					System.err.println(facadeLivros.selecionarLivro());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
				break;

			default:
				System.err.println("\n\n<< EXECUÇÃO DO PROGRAMA FINALIZADA >>\n\n");
				flag = false;
			}
		}
	}
}

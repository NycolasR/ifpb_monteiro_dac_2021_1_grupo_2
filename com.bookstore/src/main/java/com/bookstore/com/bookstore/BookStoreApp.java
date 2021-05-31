package com.bookstore.com.bookstore;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.bookstore.com.bookstore.facades.FacadeEditoras;
import com.bookstore.com.bookstore.facades.FacadeEnderecos;
import com.bookstore.com.bookstore.facades.FacadeLivros;
import com.bookstore.com.bookstore.facades.FacadeUsuarios;
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
	private FacadeUsuarios facadeUsuarios;
	private FacadeEnderecos facadeEnderecos;
	
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
			FacadeEditoras facadeEditoras,
			FacadeUsuarios facadeUsuarios,
			FacadeEnderecos facadeEnderecos) {
		
		this.usuarioService = usuarioService;
		this.registroVendasService = registroVendasService;
		this.itemPedidoService = itemPedidoService;
		this.formaPagamentoService = formaPagamentoService;
		this.autorService = autorService;
		this.pedidoService = pedidoService;
		
		this.facadeLivros = facadeLivros;
		this.facadeEditoras = facadeEditoras;
		this.facadeUsuarios = facadeUsuarios;
		this.facadeEnderecos = facadeEnderecos;
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
				
				System.out.print("Informe o nome do Usuario: ");
				String nomeCadastro = scanner.nextLine();
				
				System.out.print("Informe o e-mail do Unsuario: ");
				String emailCadastro = null;
				
				while(true) {
					emailCadastro = scanner.nextLine();
					if(facadeUsuarios.verificarExistencia(emailCadastro)) {
						break;
					}
					System.out.println("[ERROR] Endereço de e-mail indisponivel");
					System.out.println("Por favor, insira um endereço de e-mail diferente:");
				}

				
				System.out.print("Informe o senha do Unsuario: ");
				String senhaCadastro = scanner.nextLine();
				
				try {
					facadeUsuarios.cadastrarUsuario(nomeCadastro, emailCadastro, senhaCadastro);
					System.out.println("Cadastro realizado com sucesso!");
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("\n[ATENCAO] Usuarios devem ter ao menos 1 endereço cadastrado "
						+ "\nPor favor, cadastre um endereço abaixo: "
						+ "\n Rua:");
				
				String rua = scanner.nextLine();
				
				System.out.println("numero: ");
				Integer numero = Integer.parseInt(scanner.nextLine());
				
				System.out.println("bairro: ");
				String bairro = scanner.nextLine();
				
				System.out.println("UF: ");
				String uf = scanner.nextLine().toUpperCase();
				
				System.out.println("Cidade: ");
				String cidade = scanner.nextLine();
				
				System.out.println("Complemento: ");
				String complemento = scanner.nextLine();
				
				System.out.println("CEP (Sem \"-\" ou \".\"): ");
				Integer cep = Integer.parseInt(scanner.nextLine());

				try {
					facadeUsuarios.addEndereco(emailCadastro, facadeEnderecos.criarEndereco(rua, numero, bairro, uf, cidade, complemento, cep));
					System.out.println("Bem vindo a BookStore Sr(a) "+ nomeCadastro + "!");
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				break;

			case 2: // Pedro

				System.out.print("Informe o e-mail do Unsuario: ");
				String emailConsulta = scanner.nextLine();
				
				try {
					System.out.println(facadeUsuarios.consultarPorEmail(emailConsulta).toString());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("\n Prossione Enter para continuar.");
				scanner.nextLine();
				
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
				try {
					Page<Livro> livros = facadeLivros.paginarLivros("preco",Sort.Direction.ASC, 1, true);
					System.out.println("\n5 Livros mais baratos em estoque:\n");
					for(Livro l : livros) {
						System.out.println("Livro: " + l.getTitulo() + " || Preço: " + l.getPreco() );
					}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("\n Prossione Enter para continuar.");
				scanner.nextLine();
			
				break;

			case 10: // Pedro
				int pagina = 1;
				boolean condicao = true;
				while(condicao) {
					Page<Livro> livros = facadeLivros.paginarLivros("titulo", Sort.Direction.ASC, pagina, false);
					System.out.println("\nTodos os Livros:\n");
					System.out.println("Total de paginas: " + livros.getTotalPages() 
					+ " \nPagina atual: " + (livros.getNumber() + 1)  + "\n");
					
					for(Livro l : livros) {
						System.out.println("Dados do Livro: " + l.getTitulo()
								+ " ||ISBN: " + l.getISBN()
								+ " ||Descrição: " + l.getDescricao()
								+ " ||Preço: R$" + l.getPreco()
								+ " ||Edição: " + l.getEdicao()
								+ " ||Ano de Publicação: " + l.getAnoPublicacao()
								+ " ||Quantidade disponível no estoque: " + l.getQuantidadeEmEstoque() + " unidades" );
					}
					
					while(true) {
						System.out.println("\nDigite a pagina desejada: \n(Pressione Enter para sair) ");
						String proximaPagina = scanner.nextLine();
						
						if(proximaPagina.isEmpty()) {
							condicao = false;
							break;
						}else if(Integer.parseInt(proximaPagina) >0 && Integer.parseInt(proximaPagina) <= livros.getTotalPages()) {
							pagina = Integer.parseInt(proximaPagina);
							break;
						}else {
							System.err.println("\n[ERROR] Numero de pagina invalido");
						}
						
					}
						
					
						
					
				}
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

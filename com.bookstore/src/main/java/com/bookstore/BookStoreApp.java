package com.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class BookStoreApp {
	
//	public static void main(String[] args) {
//		SpringApplication.run(BookStoreApp.class, args);
//		System.out.println("Server is running");
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

	/*
	private FacadePedido facadePedido;

	public BookStoreApp(FacadePedido facadePedido) {

		this.facadePedido = facadePedido;

	}
	
	@Override
	public void run(String... args) throws Exception {

	facadePedido.criarPedido(1L, 10);
		facadePedido.finalizarPedido(1L, 1L, 14L);

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
				try {
					
					String nomeCadastro = validarEntrada("Informe o nome do Usuario: ",false,true);					
					
					String emailCadastro = null;
					
					while(true) {
						emailCadastro = validarEntrada("Informe o e-mail do Unsuario: ",false,true);
						if(!facadeUsuarios.verificarExistencia(emailCadastro)) {
							break;
						}
						System.err.println("[ERROR] Endereço de e-mail indisponivel");
						System.out.println("Por favor, insira um endereço de e-mail diferente:");
					}
					
					String senhaCadastro = validarEntrada("Informe o senha do Unsuario: ",false,true);
					
					System.out.println("\n[ATENCAO] Usuarios devem ter ao menos 1 endereço cadastrado "
							+ "\nPor favor, cadastre um endereço abaixo. ");
					
					System.out.println("\nCadastro de Endereço:");
					
					String rua = validarEntrada("Rua: ",false,true);
					Integer numero = Integer.parseInt(validarEntrada("numero: ",true,true));
					String bairro = validarEntrada("bairro: ",false,true);
					String uf = validarEntrada("UF: ",false,true).toUpperCase();
					String cidade = validarEntrada("Cidade: ",false,true);
					String complemento = validarEntrada("Complemento: ",false,true);
					Integer cep = Integer.parseInt(validarEntrada("CEP (Sem \"-\" ou \".\"): ",true,true));
					
					try {
						facadeUsuarios.cadastrarUsuario(nomeCadastro, emailCadastro, senhaCadastro);
						facadeUsuarios.addEndereco(emailCadastro, facadeEnderecos.criarEndereco(rua, numero, bairro, uf, cidade, complemento, cep));
						System.err.println();
						System.out.println("Cadastro realizado com sucesso! \nBem vindo a BookStore Sr(a) "+ nomeCadastro + "!");
					}catch (Exception e) {
						System.err.println(e.getMessage());
						break;
					}
				
				}catch ( Exception e ){
					System.err.println(e.getMessage());
				}
				
				break;

			case 2: // Pedro

				String emailConsulta = validarEntrada("Informe o e-mail do Unsuario: ", false,true);
				
				try {
					System.out.println(facadeUsuarios.consultarPorEmail(emailConsulta).toString());
				}catch (Exception e) {
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("\n Pressione Enter para continuar.");
				scanner.nextLine();
				
				break;

			case 3: // Gabriel - Cadastrar Autor

				codigoCase3();
				
				break;

			case 4: // Gabriel - Alterar Autor
				
				Autor autorEditado = null;
				
				try {
					 autorEditado = facadeAutor.selecionarAutor(false);
					 System.err.println(autorEditado);
				}catch (Exception e) {
					System.out.println(e.getMessage());
					break;
				}
				
				//Ler dados a respeito do Autor
				String novoNomeAutor = validarEntrada("Informe o novo nome do Autor: ", false,true);
				
				System.out.println("\nDados atualizados:");
				System.err.println(autorEditado);
				
				autorEditado.setNome(novoNomeAutor);
				facadeAutor.atualizarAutor(autorEditado);

				break;

			case 5: // Nycolas - Cadastrar Livro
				
				// Ler dados do livro
				String titulo = validarEntrada("Informe o título do livro: ", false,true);
				
				Long isbn = Long.parseLong(validarEntrada("Informe o ISBN do livro: ", true,true));
				
				String descricao = validarEntrada("Informe a descrição do livro: ", false,true);
				
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
				
				Integer edicao = Integer.parseInt(validarEntrada("Informe a edição do livro: ", true,true));
				
				Integer anoPublicacao = Integer.parseInt(validarEntrada("Informe o ano de publicação do livro: ", true,true));
				
				Integer qntdEstoque = Integer.parseInt(validarEntrada("Informe a quantidade de unidades do livro para estoque: ", true,true));
				
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
				Autor autorSelecionado = null;
				
				System.out.println("\nAgora, selecione o autor deste livro.");
				try {
					autorSelecionado = facadeAutor.selecionarAutor(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					System.err.println("Será necessário que você cadastre ao menos 1 autor.");
					autorSelecionado = codigoCase3();
				}
				
				// Seta tudo um no outro e salva
				Long idLivro = facadeLivros.salvarLivro(isbn, titulo, descricao, preco, edicao, anoPublicacao, qntdEstoque);
				Livro livro = facadeLivros.recuperarLivro(idLivro);
				
				editoraSelecionada.addLivro(livro);
				facadeEditoras.atualizarEditora(editoraSelecionada);
				
				autorSelecionado.adicionarLivro(livro);
				facadeAutor.atualizarAutor(autorSelecionado);
				
				// Mostra os dados salvos pela console

				System.err.println(editoraSelecionada);
				System.err.println(autorSelecionado);
				System.err.println(livro);
				
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
					break;
				}
				break;

			case 8: // Nycolas - Cadastrar um livro do catálogo ao estoque
				
				Livro livoSelecionado = null;
				
				try {
					livoSelecionado = facadeLivros.selecionarLivro();
					System.err.println(livoSelecionado);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					break;
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
					Page<Livro> livros = null;
					try {
						livros = facadeLivros.paginarLivros("titulo", Sort.Direction.ASC, pagina, false);
					}catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					}
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
						String proximaPagina = validarEntrada("\nDigite a pagina desejada: \n(Pressione Enter para sair) ", true, false);
						
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

			case 11: // Gabriel - Adicionar um livro a um pedido (carrinho de compras)
				
				
				//Ler dados a respeito do Pedido
				try {
					Long idLivroretornado = facadeLivros.selecionarLivro().getId();
					
					System.out.print("Informe a quantidade : ");
					Integer quantidade = Integer.parseInt(scanner.nextLine());
					
//					System.out.print("Informe o e-mail do Unsuario: ");
//					String emailCadastroRetornado = scanner.nextLine();
					
					facadePedido.criarPedido(idLivroretornado, quantidade);
					
				}catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				break;

			case 12: // Nycolas - Ler dados de 1 livro
				
				try {
					System.err.println(facadeLivros.selecionarLivro());
				} catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				break;

			default:
				System.err.println("\n\n<< EXECUÇÃO DO PROGRAMA FINALIZADA >>\n\n");
				flag = false;
			}
		}
	}

	private Autor codigoCase3() throws Exception {
		//Ler dados a respeito do Autor
		System.out.print("Informe o nome do Autor: ");
		String nomeAutor = new Scanner(System.in).nextLine();
		
		return facadeAutor.criarAutor(nomeAutor);
	}
	
	/**
	 * Método usado para fazer a validação de uma entrada.
	 * 
	 * @param texto String que deve ser apresentado ao usuario.
	 * @param isNumero Bollean que diz se a entrada deve ser apenas numerica ou não.
	 * @param isObrigatorio Boolean que diz se a resposta do usuario é obrigatoria ou não.
	 * @return retorna uma string que é a entrada já validada. 
	 * @throws Exception Lança uma exceção caso o usuario queria cancelar oq esta fazendo.
	 
	private String validarEntrada(String texto, boolean isNumero, boolean isObrigatorio) throws Exception{ 
		Scanner scannerValidacao = new Scanner(System.in);	
		
		String entrada = null;
		
		System.out.println(texto);
		while(true) {
			entrada = scannerValidacao.nextLine();
			
			if(isObrigatorio == false && entrada.trim().isEmpty()){
				break;
			}else if((isObrigatorio == true && entrada.trim().isEmpty()) || (isNumero == true && (entrada.matches("^[0-9]+$") == false))) {
				System.err.println("[ERROR] Entrada invalida, tente novamente! \nDiginte \"close\" para cancelar.");
			}else if(entrada.toLowerCase().equals("close")) {
				throw new Exception("Operacao cancelada.");
			}else{
				break;
			}	
			System.out.println(texto);
		}
		return entrada;
	}
*/

	
}

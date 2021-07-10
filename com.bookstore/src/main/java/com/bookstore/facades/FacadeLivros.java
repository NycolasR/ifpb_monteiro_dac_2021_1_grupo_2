package com.bookstore.facades;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bookstore.model.Livro;
import com.bookstore.service.LivroService;

/**
 * 
 * @author NPG (Grupo 2)
 * Classe Façade usada para interfacear certas funcionalidades relativas a Livro.
 * Esse padrão de projeto foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 */
@Component
public class FacadeLivros {
	
	@Autowired
	private LivroService livroService;
	
	/**
	 * Método usado para criar um livro e salvá-lo
	 * @param isbn ISBN do livro
	 * @param titulo Título do livro
	 * @param descricao Descrição do livro
	 * @param preco Preço do livro
	 * @param edicao Edição do livro
	 * @param anoPublicacao Ano de publicação do livro
	 * @param qntdEstoque Quantidade do livro que estará em estoque
	 * @return O registro de Livro pronto
	 */
	public Long salvarLivro(
			Long isbn, 
			String titulo, 
			String descricao, 
			BigDecimal preco, 
			Integer edicao, 
			Integer anoPublicacao, 
			Integer qntdEstoque) {
		
		Livro livro = new Livro(isbn, titulo, descricao, preco, edicao, anoPublicacao, qntdEstoque);
		livroService.salvarLivro(livro);
		return livro.getId();
	}
	
	/**
	 * Método usado para atualizar um registro de Livro de forma flexível
	 * @param livro Livro que se deseja atualizar
	 * @param atributo Atributo do livro que está para ser atualizado
	 */
	public void atualizarAtributoLivro(Livro livro, String atributo) {
		Scanner input = new Scanner(System.in);
		String resposta = input.nextLine();

		if(!resposta.isEmpty()) {

			switch (atributo) {
			case "titulo":
				livro.setTitulo(resposta);
				break;

			case "ISBN":
				Long isbn = Long.parseLong(resposta);
				
				if(isbn < 0)
					System.err.println("[ERRO] Não é permitido registrar ISBNs negativos");
				else
					livro.setISBN(Long.parseLong(resposta));
				
				break;
				
			case "descricao":
				livro.setDescricao(resposta);
				break;
				
			case "preco":
				while(true) {
					try {
						livro.setPreco(new BigDecimal(resposta));
						break;
					} catch(NumberFormatException e) {
						System.err.print("[ERRO] Insira um formato de preço válido: ");
						resposta = input.nextLine();
					}
					
				}
				break;
				
			case "edicao":
				livro.setEdicao(Integer.parseInt(resposta));
				break;
				
			case "anoPublicacao":
				livro.setAnoPublicacao(Integer.parseInt(resposta));
				break;
				
			case "quantidadeEmEstoque":
				
				while(Integer.parseInt(resposta) <= 0) {
					System.err.print("[ERRO] Insira um valor não negativo: ");
					resposta = input.nextLine();
				}
				
				Integer novaQuantidade = livro.getQuantidadeEmEstoque() + Integer.parseInt(resposta);
				livro.setQuantidadeEmEstoque(novaQuantidade);
				break;

			default:
				break;
			}
		}
	}
	
	/**
	 * Método usado para recuperar o registro de um Livro
	 * @param id ID do livro que se deseja obter
	 * @return O registro caso encontrado
	 * @throws Exception Lança exceção se o registro não for encontrado
	 */
	public Livro recuperarLivro(Long id) throws Exception {
		Optional<Livro> optional = livroService.recuperarPeloId(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("[ERRO] Livro não encontrado na base de dados.");
	}

	/**
	 * Método usado para listar e selecionar um livro
	 * @return O livro selecionado 
	 * @throws Exception Lança exceção caso não existam livros registrados
	 */
	public Livro selecionarLivro() throws Exception {
		
		if(livroService.existemRegistros()) {
			Scanner input = new Scanner(System.in);
			
			List<Livro> livros = livroService.listarLivros();
			
			System.out.println("Livros Cadastrados:\n");
			for (Livro livro : livros) {
				System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo());
			}
			
			System.err.print("Informe o ID do livro selecionado: ");
			Long id = Long.parseLong(input.nextLine());
			
			while(true) {
				try {
					return recuperarLivro(id);
				} catch (Exception e) {
					System.err.print(e.getMessage() + " Informe o ID novamente: ");
					id = Long.parseLong(input.nextLine());
				}
			}
		}
		
		throw new Exception("[ERRO] Não existem livros cadastrados no sistema.");
	}
	
	public Page<Livro> paginarLivros(String campoOrdenacao, Sort.Direction sortDirection, Integer numeroPagina, boolean inEstoque) throws Exception{
		Page<Livro> pagTemp = livroService.listarLivros(campoOrdenacao, sortDirection, numeroPagina, inEstoque);
		if(!pagTemp.isEmpty()) {
			return pagTemp;			
		}
		throw new Exception("[ERRO] Nenhum Livro cadastrado");
	}
	
	/**
	 * Método usado para deletar um registro de livro
	 * @param livro Livro que está para ser deletado
	 */
	public void deletarLivro(Livro livro) {
		livroService.deletarPeloId(livro.getId());
	}

	/**
	 * Método usado para atualizar um registro de livro
	 * @param livro Livro que está para ser atualizado
	 */
	public void atualizarLivro(Livro livroEditado) {
		livroService.atualizarLivro(livroEditado);
	}
}

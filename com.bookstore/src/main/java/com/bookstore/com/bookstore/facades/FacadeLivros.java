package com.bookstore.com.bookstore.facades;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.service.LivroService;

@Component
public class FacadeLivros {
	
	@Autowired
	private LivroService livroService;
	
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
	
	public Livro recuperarLivro(Long id) throws Exception {
		Optional<Livro> optional = livroService.recuperarPeloId(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("[ERRO] Livro não encontrado na base de dados.");
	}

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
	
	public void deletarLivro(Livro livro) {
		livroService.deletarPeloId(livro.getId());
	}

	public void atualizarLivro(Livro livroEditado) {
		livroService.atualizarLivro(livroEditado);
	}
}

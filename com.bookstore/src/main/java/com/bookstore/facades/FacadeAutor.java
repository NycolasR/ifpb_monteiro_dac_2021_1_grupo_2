package com.bookstore.facades;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Autor;
import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.service.AutorService;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe Facade responsável por métodos relacionados a Autor. Esse padrão de projeto
 * foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadeAutor {

	@Autowired
	private AutorService autorService;
	
	/**
	 * Método responsável por criar um autor
	 * @param nome nome do autor
	 * @return retorna o Autor criado
	 * @throws Exception lança exceção caso ele não tenha sido cadastrado corretamente ao banco
	 */
	public Autor criarAutor(String nome) throws Exception {
		
		Autor autor = new Autor(nome);
		autorService.salvarAutor(autor);
		
		return recuperarAutor(autor.getID());
		
	}
	
	/**
	 * Esse método remove um Autor pelo seu id
	 * @param id id correspondente ao Autor
	 * @throws Exception lança exceção caso não seja encontrado o Autor
	 */
	public void removerAutor(Long id) throws Exception{
		
		recuperarAutor(id);
		autorService.deletarPeloId(id);
	}
	
	/**
	 * Esse método recupera um Autor pelo seu id
	 * @param id id correspondente ao Autor
	 * @return retorna o Autor encontrado
	 * @throws Exception lança exceção caso não seja encontrado o Autor
	 */
	public Autor recuperarAutor(Long id) throws Exception{
		
	    Optional<Autor> autor = autorService.recuperarPeloId(id);
		
	    if(autor.isPresent()) {
	    	return autor.get();
	    }
		
		throw new Exception("[ERRO] Autor inexistente");
	}
	
	/**
	 * Esse método atualiza um Autor 
	 * @param autor Autor que se deseja atualizar
	 */
	public void atualizarAutor(Autor autor) {
		
		autorService.atualizarAutor(autor);
	}
	
	/**
	 * Esse método será utilizado no main, no entando existe uma alta probabilidade do mesmo ser excluido posteriormente
	 * @return Autor encontrado
	 * @throws Exception caso não existam registros de autores
	 */
	public Autor selecionarAutor(boolean isPerguntar) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		if(autorService.existemRegistros()) {
			
			List<Autor> autores = autorService.recuperarAutores();
			
			System.out.println("Autores Cadastrados:\n");
			for (Autor autor : autores) {
				System.out.println("ID: " + autor.getID() + " | Nome: " + autor.getNome());
			}
			
			String resposta = "N";
			
			if(isPerguntar) {
				System.out.print("Deseja salvar um novo autor? (Y/N) ");
				resposta = scanner.nextLine();	
			}

			
			if(resposta.equalsIgnoreCase("Y")) {
				System.out.print("Informe o nome do autor: ");
				String nome = scanner.nextLine();
								
				return criarAutor(nome);
			} else {
				
				System.err.print("Informe o ID do autor selecionado: ");
				Long id = Long.parseLong(scanner.nextLine());
				
				while(true) {
					try {
						return recuperarAutor(id);
					} catch (Exception e) {
						System.err.print(e.getMessage() + " Informe o ID novamente: ");
						id = Long.parseLong(scanner.nextLine());
					}
				}
			}
			
		}
		
		throw new Exception("[ERRO] Não existem autores cadastrados no sistema.");
	}
	
}

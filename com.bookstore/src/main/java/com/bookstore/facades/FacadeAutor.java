package com.bookstore.facades;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.model.Autor;
import com.bookstore.model.Livro;
import com.bookstore.service.AutorService;


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
	public Autor criarAutor(Autor autor) throws Exception {
		
//		Autor autor = new Autor(nome);
		autorService.salvarAutor(autor);
		
		return recuperarAutor(autor.getID());
		
	}
	
	/**
	 * Esse método remove um Autor pelo seu id caso o mesmo não esteja sendo utilizado em algum livro
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
	 * Método responsável por recuperar os autores cadastrados no banco
	 * @return retorna a lista de autores cadastrados no banco
	 */
	public List<Autor> recuperarAutores(){
		
		return autorService.recuperarAutores();
	}
	
	/**
	 * Método retorna uma entidade com os atributos nulos, ou seja, vazios, para serem adicionados
	 * nos campos do fromulário
	 * @return retorna um Autor com os atributos vazios
	 */
	public Autor recuperarAutorNulo() {
	
		return new Autor("");
	}
	
	/**
	 * Esse método atualiza um Autor 
	 * @param autorDto autor com novos parâmetros para atualizar
	 * @param id do autor
	 * @throws Exception lança escessão caso não encontre o autor com id passado
	 */
	public void atualizarAutor(Autor autorDto, Long id) throws Exception {
		
		Autor autorUpdate = recuperarAutor(id);
				
		autorUpdate.setNome(autorDto.getNome());
		
		if(autorDto.getLivros().size() > 0) {
			autorUpdate.setLivros(autorDto.getLivros());
		}
		autorService.atualizarAutor(autorUpdate);
	}
	
	public void salvarLivroAosAutores(Set<Autor> autores, Livro livro) throws Exception{
		
		for(Autor autor : autores) {
			
			autor.adicionarLivro(livro);
			atualizarAutor(autor, autor.getID());
		}
	}
}

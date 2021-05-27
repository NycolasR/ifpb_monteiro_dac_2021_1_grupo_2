package com.bookstore.com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.com.bookstore.model.Autor;
import com.bookstore.com.bookstore.model.ItemPedido;
import com.bookstore.com.bookstore.repository.AutorRepository;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos de CRUD sobre o Autor, que auxiliará na classe Livro
 *
 */
@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	/**
	 * Esse método salva (adicona ao banco de dados) um Autor de cada vez
	 * @param autor a ser adicionado ao banco
	 */
	public void salvarAutor(Autor autor) {
		
		autorRepository.save(autor);
	}
	
	/**
	 * Esse método recupera um Autor específico pelo seu ID
	 * @param id correspondente ao autor
	 * @return retorna um Optical contendo o autor encontrado
	 */
	public Optional<Autor> recuperarPeloId(Long id) {
		
		return autorRepository.findById(id);
	}
	
	/**
	 * Método responsável por recuperar uma lista de todos os autores
	 * @return lista de Autor
	 */
	public List<Autor> recuperarAutores(){
		
		return autorRepository.findAll();
	}
	
	/**
	 * Método responsável por deletar todos os autores do banco de dados
	 */
	public void deletarTodosAutores() {
		
		autorRepository.deleteAll();
	}
	
	/**
	 * Método responsável por deletar um Autor específico no 
	 * banco de dados
	 * @param id "Código" relacionado ao Autor específico
	 * a ser deletado
	 */
	public void deletarPeloId(Long id) {
		
		Optional<Autor> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			autorRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método responsável por atualizar um Autor
	 * @param autor a ser atualizado
	 */
	public void atualizarAutor(Autor autor) {
		salvarAutor(autor);
	}
}

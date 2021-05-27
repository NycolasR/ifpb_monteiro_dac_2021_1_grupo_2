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

	public void salvarAutor(Autor autor) {
		
		autorRepository.save(autor);
	}
	
	public Optional<Autor> recuperarPeloId(Long id) {
		
		return autorRepository.findById(id);
	}
	
	public List<Autor> recuperarAutores(){
		
		return autorRepository.findAll();
	}
	
	public void deletarTodosAutores() {
		
		autorRepository.deleteAll();
	}
	
	public void deletarPeloId(Long id) {
		
		Optional<Autor> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			autorRepository.delete(deletado.get());
		}
	}
	
	public void atualizarAutor(Autor autor) {
		salvarAutor(autor);
	}
}

package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.com.bookstore.model.Autor;
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
	
	public Autor recuperarpPeloCodigo(Long id) {
		
		return autorRepository.findById(id).get();
	}
}

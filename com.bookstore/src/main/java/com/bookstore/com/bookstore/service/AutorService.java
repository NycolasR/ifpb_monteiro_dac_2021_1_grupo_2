package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.com.bookstore.model.Autor;
import com.bookstore.com.bookstore.repository.AutorRepository;

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

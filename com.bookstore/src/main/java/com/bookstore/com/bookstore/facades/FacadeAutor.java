package com.bookstore.com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Autor;
import com.bookstore.com.bookstore.service.AutorService;

@Component
public class FacadeAutor {

	@Autowired
	private AutorService autorService;
	
	public void criarAutor() {
		
	}
	
	public void removerAutor(Long id) {
		
	}
	
	public Autor recuperarAutor(Long id) throws Exception{
		
		return null;
	}
	
	public void atualizarAutor(Autor autor) {
		
	}
}

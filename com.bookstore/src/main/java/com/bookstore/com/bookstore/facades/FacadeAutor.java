package com.bookstore.com.bookstore.facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Autor;
import com.bookstore.com.bookstore.service.AutorService;

@Component
public class FacadeAutor {

	@Autowired
	private AutorService autorService;
	
	public void criarAutor(String nome) {
		
		Autor autor = new Autor(nome);
		autorService.salvarAutor(autor);
		
	}
	
	public void removerAutor(Long id) throws Exception{
		
		recuperarAutor(id);
		autorService.deletarPeloId(id);
	}
	
	public Autor recuperarAutor(Long id) throws Exception{
		
	    Optional<Autor> autor = autorService.recuperarPeloId(id);
		
	    if(autor.isPresent()) {
	    	return autor.get();
	    }
		
		throw new Exception("[ERRO] Autor inexistente");
	}
	
	public void atualizarAutor(Autor autor) {
		
		autorService.atualizarAutor(autor);
	}
}

package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void salvar(Livro livro) {
		livroRepository.save(livro);
	}
	
	public Livro recuperarPeloId(Long id) {
		return livroRepository.findById(id).get();
	}
	
	public void atualizar(Livro livro) {
		
	}
}

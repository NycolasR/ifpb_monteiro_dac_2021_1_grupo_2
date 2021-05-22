package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Estoque;
import com.bookstore.com.bookstore.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository; 
	
	
	public void salvar(Estoque estoque) {
		estoqueRepository.save(estoque);
	}
	
	public Estoque recuperarPeloId(Long id) {
		return estoqueRepository.findById(id).get();
	}
	
}

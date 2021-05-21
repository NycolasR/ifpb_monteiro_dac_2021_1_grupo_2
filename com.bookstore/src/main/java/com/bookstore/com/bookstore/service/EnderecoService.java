package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.com.bookstore.model.Endereco;
import com.bookstore.com.bookstore.repository.EnderecoRepository;

public class EnderecoService {

	@Autowired
	public EnderecoRepository enderecoRepository;
	
	public void salvar(Endereco endereco) {
		enderecoRepository.save(endereco);
		
	}
	
	
	
}

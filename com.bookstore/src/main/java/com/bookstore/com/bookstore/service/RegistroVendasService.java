package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.RegistroVendas;
import com.bookstore.com.bookstore.repository.RegistroVendasRepository;

@Service
public class RegistroVendasService {
	
	@Autowired
	private RegistroVendasRepository registroVendasRepository; 
	
	
	public void salvar(RegistroVendas estoque) {
		registroVendasRepository.save(estoque);
	}
	
	public RegistroVendas recuperarPeloId(Long id) {
		return registroVendasRepository.findById(id).get();
	}
	
}

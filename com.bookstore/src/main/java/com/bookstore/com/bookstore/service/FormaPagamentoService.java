package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.FormaPagamento;
import com.bookstore.com.bookstore.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public void salvarFormaPagamento(FormaPagamento formaPagamento) {
		
		formaPagamentoRepository.save(formaPagamento);
	}
	
	public FormaPagamento recuperarPeloCodigo(Long id) {
		
		return formaPagamentoRepository.findById(id).get();
	}
}

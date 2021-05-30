package com.bookstore.com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Pedido;
import com.bookstore.com.bookstore.service.PedidoService;

@Component
public class FacadePedido {

	@Autowired
	private PedidoService pedidoService;
	
	public void criarPedido() {
		
	}
	
	public void removerPedido(Long id) {
		
	}
	
	public Pedido recuperarPedido(Long id) {
		
		return null;
	}
	
	public void atualizarPedido(Pedido pedido) {
		
	}
	
	public void finalizarPedido(Long id) {
		
	}
	
}

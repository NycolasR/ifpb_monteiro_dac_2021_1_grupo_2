package com.bookstore.com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Autor;
import com.bookstore.com.bookstore.model.Pedido;
import com.bookstore.com.bookstore.repository.PedidoRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos de CRUD sobre o Pedido
 *
 *
 */
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void salvarPedido(Pedido pedido) {
		
		pedidoRepository.save(pedido);
	}
	
	public Optional<Pedido> recuperarPeloId(Long id) {
		
		return pedidoRepository.findById(id);
	}
	
	public List<Pedido> recuperarPedidos(){
		
		return pedidoRepository.findAll();
	}
	
	public void deletarTodosPedidos() {
		
		pedidoRepository.deleteAll();
	}
	
	public void deletarPeloId(Long id) {
		
		Optional<Pedido> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			pedidoRepository.delete(deletado.get());
		}
	}
	
	public void atualizarPedido(Pedido pedido) {
		salvarPedido(pedido);
	}

}

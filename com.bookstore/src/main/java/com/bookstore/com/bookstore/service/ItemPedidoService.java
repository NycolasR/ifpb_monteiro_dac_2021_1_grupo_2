package com.bookstore.com.bookstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.com.bookstore.model.ItemPedido;
import com.bookstore.com.bookstore.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void salvarItemPedido(ItemPedido itemPedido) {
		
		itemPedidoRepository.save(itemPedido);
	}
	
	public Optional<ItemPedido> recuperarPeloId(Long id) {
		
		return itemPedidoRepository.findById(id);
	}
	
	public List<ItemPedido> recuperarItensPedidos(){
		
		return itemPedidoRepository.findAll();
	}
	
	public void deletarTodosItensPedidos() {
		
		itemPedidoRepository.deleteAll();
	}
	
	public void deletarPeloId(Long id) {
		
		Optional<ItemPedido> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			itemPedidoRepository.delete(deletado.get());
		}
	}
	
	public void atualizarItemPedido(ItemPedido itemPedido) {
		salvarItemPedido(itemPedido);
	}
	
	
	
	
}

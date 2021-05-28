package com.bookstore.com.bookstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.com.bookstore.model.ItemPedido;
import com.bookstore.com.bookstore.repository.ItemPedidoRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos de CRUD sobre o ItemPedido
 *
 */
@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	/**
	 * Esse método salva (adicona ao banco de dados) um ItemPedido de cada vez
	 * @param itemPedido a ser adicionado
	 */
	public void salvarItemPedido(ItemPedido itemPedido) {
		
		itemPedidoRepository.save(itemPedido);
	}
	
	/**
	 * Esse método recupero um ItemPedido específico pelo seu ID
	 * @param id "Código" relacionado ao ItemPedido buscado
	 * @return retorna um Optical contendo o ItemPedido encontrado
	 */
	public Optional<ItemPedido> recuperarPeloId(Long id) {
		
		return itemPedidoRepository.findById(id);
	}
	
	/**
	 * Esse método recupera uma lista de todos os ItemPedido
	 * @return lista de IteMPedido
	 */
	public List<ItemPedido> recuperarItensPedidos(){
		
		return itemPedidoRepository.findAll();
	}
	
	/**
	 * Esse método deleta todos os registros salvos no banco de dados
	 * a respeito de ItemPedido
	 */
	public void deletarTodosItensPedidos() {
		
		itemPedidoRepository.deleteAll();
	}
	
	/**
	 * Método responsável por deletar um ItemPedido específico no 
	 * banco de dados
	 * @param id "Código" relacionado ao ItemPedido específico
	 * a ser deletado
	 */
	public void deletarPeloId(Long id) {
		
		Optional<ItemPedido> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			itemPedidoRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método responsável por atualizar um ItemPedido
	 * @param itemPedido a ser atualizado
	 */
	public void atualizarItemPedido(ItemPedido itemPedido) {
		salvarItemPedido(itemPedido);
	}
	
	
	
	
}

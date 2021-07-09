package com.bookstore.service;

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
	
	/**
	 * Esse método salva (adicona ao banco de dados) um Pedido de cada vez
	 * @param pedido a ser adicionado no banco de dados
	 */
	public void salvarPedido(Pedido pedido) {
		
		pedidoRepository.save(pedido);
	}
	
	/**
	 * Esse método recupera um Pedido específico pelo seu ID
	 * @param id correspondente ao Pedido
	 * @return retorna um Optical contendo o pedido encontrado
	 */
	public Optional<Pedido> recuperarPeloId(Long id) {
		
		return pedidoRepository.findById(id);
	}
	
	/**
	 * Método responsável por recuperar uma lista de todos os pedidos
	 * @return uma lista de todos os pedidos registrados no banco de dados
	 */
	public List<Pedido> recuperarPedidos(){
		
		return pedidoRepository.findAll();
	}
	
	/**
	 * Esse método deleta todos os registros do banco de dados relacionados 
	 * ao Pedido
	 */
	public void deletarTodosPedidos() {
		
		pedidoRepository.deleteAll();
	}
	
	/**
	 * Método responsável por deletar um Pedido específico no 
	 * banco de dados
	 * @param id "Código" relacionado ao Pedido específico
	 * a ser deletado
	 */
	public void deletarPeloId(Long id) {
		
		Optional<Pedido> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			pedidoRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método responsável por atualizar um Autor
	 * @param pedido a ser atualizado
	 */
	public void atualizarPedido(Pedido pedido) {
		salvarPedido(pedido);
	}

}

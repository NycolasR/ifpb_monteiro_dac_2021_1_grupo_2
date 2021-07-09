package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.FormaPagamento;
import com.bookstore.com.bookstore.model.ItemPedido;
import com.bookstore.com.bookstore.repository.FormaPagamentoRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos de CRUD sobre o FormaPagamento
 *
 */
@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	/**
	 * Esse método salva (adicona ao banco de dados) uma FormaPagamento de cada vez
	 * @param formaPagamento a ser adicionada no banco
	 */
	public void salvarFormaPagamento(FormaPagamento formaPagamento) {
		
		formaPagamentoRepository.save(formaPagamento);
	}
	
	/**
	 * Esse método recupera uma FormaPagamento específica pelo seu ID
	 * @param id correspondente a FormaPagamento buscada
	 * @return retorna um Optical contendo a FormaPagamento encontrada
	 */
	public Optional<FormaPagamento> recuperarPeloId(Long id) {
		
		return formaPagamentoRepository.findById(id);
	}
	
	/**
	 * Esse método recupera uma FormaPagamento específica pelo seu tipo
	 * @param tipo correspondente a FormaPagamento buscada
	 * @return retorna uma FormaPagamento encontrada
	 */
	public FormaPagamento recuperarPeloTipo(String tipo){
		
		return formaPagamentoRepository.findByTipo(tipo);
	}
	
	/**
	 * Esse método recupera uma lista de todas as FormaPagamento
	 * @return lista de FormaPagamento
	 */
	public List<FormaPagamento> recuperarFormasPagamento(){
		
		return formaPagamentoRepository.findAll();
	}
	
	/**
	 * Esse método deleta todos os registros salvos no banco de dados
	 * a respeito de FormaPagamento
	 */
	public void deletarTodasFormasPagamento() {
		
		formaPagamentoRepository.deleteAll();
	}
	
	/**
	 * Método responsável por deletar uma FormaPagamento específica no 
	 * banco de dados
	 * @param id "Código" relacionado a FormaPagamento específica
	 * a ser deletada
	 */
	public void deletarPeloId(Long id) {
		
		Optional<FormaPagamento> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			formaPagamentoRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método responsável por atualizar uma FormaPagamento
	 * @param formaPagamento a ser atualizada
	 */
	public void atualizarFormaPagamento(FormaPagamento formaPagamento) {
		salvarFormaPagamento(formaPagamento);
	}

}

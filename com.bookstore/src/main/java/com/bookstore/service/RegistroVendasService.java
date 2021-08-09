package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.RegistroVendas;
import com.bookstore.repository.RegistroVendasRepository;

/**
 * 
 * @author NPG (Grupo 2 - Projeto de DAC)
 * Classe responsável por fornecer os serviços de CRUD relativos a entidade RegistroVendas.
 * 
 */
@Service
public class RegistroVendasService {
	
	@Autowired
	private RegistroVendasRepository registroVendasRepository; 
	
	/**
	 * Método usado para salvar no banco de dados um novo registro de RegistroVendas
	 * @param registro Objeto que se deseja salvar no banco de dados
	 */
	public void salvarRegistroVendas(RegistroVendas registro) {
		registroVendasRepository.save(registro);
	}
	
	/**
	 * Método usado para retornar um Optional de RegistroVendas
	 * identificada pelo seu ID
	 * @param id ID do registro que se deseja obter da tabela
	 * @return O Optional de RegistroVendas que contém o registro
	 * requisitado, caso seja encontrado.
	 */
	public Optional<RegistroVendas> recuperarPeloId(Long id) {
		return registroVendasRepository.findById(id);
	}
	
	/**
	 * Método usado para retornar todos os registros na tabela de RegistroVendas
	 * @return List<RegistroVendas> contendo todos os registros da tabela ou uma lista
	 * vazia caso não hajam registros salvos.
	 */
	public List<RegistroVendas> listarRegistrosDeVendas() {
		return registroVendasRepository.findAll();
	}
	
	/**
	 * Método usado para deletar todos os registros da tabela de registros de vendas.
	 */
	public void deletarTodosRegistrosDeVendas() {
		registroVendasRepository.deleteAll();
	}
	
	/**
	 * Método que retorna quantas vendas foram realizadas na livraria
	 * @return quantidade de vendas realizadas
	 */
	public Long quantidadeDeVendas() {
		
		return registroVendasRepository.count();
	}
	
	/**
	 * Método usado para deletar um dado registro de vendas na tabela identificado
	 * pelo seu ID
	 * @param id ID do registro que se deseja deletar
	 */
	public void deletarPeloId(Long id) {
		Optional<RegistroVendas> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			registroVendasRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método usado para atualizar um registro de uma venda no banco de dados.
	 * Tem o comportamento igual ao metodo salvarRegistroVendas(RegistroVendas registro), mas foi criado
	 * para maior coesão e acessibilidade nesta classe de CRUD. 
	 * @param registro Registro de venda que se deseja atualizar no banco de dados.
	 */
	public void atualizarRegistroDeVenda(RegistroVendas registro) {
		salvarRegistroVendas(registro);
	}
}





















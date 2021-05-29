package com.bookstore.com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.repository.EditoraRepository;

/**
 * 
 * @author NPG (Grupo 2 - Projeto de DAC)
 * Classe responsável por fornecer os serviços de CRUD relativos a entidade Editora.
 * 
 */
@Service
public class EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;
	
	/**
	 * Método usado para salvar no banco de dados um novo registro de Editora
	 * @param editora Objeto que se deseja salvar no banco de dados
	 */
	public void salvarEditora(Editora editora) {
		editoraRepository.save(editora);
	}
	
	/**
	 * Método usado para retornar um Optional de Editora
	 * identificada pelo seu ID
	 * @param id ID do registro que se deseja obter da tabela
	 * @return O Optional de Editora que contém o registro da editora
	 * requisitada, caso seja encontrado.
	 */
	public Optional<Editora> recuperarPeloId(Long id) {
		return editoraRepository.findById(id);
	}
	
	/**
	 * Método usado para retornar todos os registros na tabela de editora
	 * @return List<Editora> contendo todos as editoras da tabela ou uma lista
	 * vazia caso não hajam registros.
	 */
	public List<Editora> listarEditoras() {
		return editoraRepository.findAll();
	}
	
	/**
	 * Método usado para deletar todos os registros da tabela de editoras.
	 */
	public void deletarTodasEditoras() {
		editoraRepository.deleteAll();
	}
	
	/**
	 * Método usado para deletar um dado registro na tabela identificado
	 * pelo seu ID
	 * @param id ID do registro que se deseja deletar
	 */
	public void deletarPeloId(Long id) {
		Optional<Editora> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			editoraRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método usado para atualizar um registro de uma editora no banco de dados.
	 * Tem o comportamento igual ao metodo salvarEditora(Editora editora), mas foi criado
	 * para maior coesão e acessibilidade nesta classe de CRUD. 
	 * @param editora Editora que se deseja atualizar no banco de dados.
	 */
	public void atualizarEditora(Editora editora) {
		salvarEditora(editora);
	}
	
	/**
	 * Método usado para saber se existem registros de Editora no banco de dados
	 * @return True se existir pelo menos um registro.
	 */
	public boolean existemRegistros() {
		return editoraRepository.count() > 0;
	}
}


















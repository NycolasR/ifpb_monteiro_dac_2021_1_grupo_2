package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Categoria;
import com.bookstore.repository.CategoriaRepository;

/**
 * 
 * @author NPG (Grupo 2 - Projeto de DAC)
 * Classe responsável por fornecer os serviços de CRUD (Update não necessário) relativos a entidade Categoria.
 * 
 */
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Método usado para salvar no banco de dados um novo registro de Categoria
	 * @param categoria Objeto que se deseja salvar no banco de dados.
	 */
	public void salvarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	
	/**
	 * Método usado para retornar um Optional de Categoria
	 * identificado pelo seu ID
	 * @param id ID da categoria que se deseja obter da tabela
	 * @return O Optional de Categoria que contém o registro do categoria
	 * requisitado, caso seja encontrado.
	 */
	public Optional<Categoria> recuperarPeloId(Long id) {
		return categoriaRepository.findById(id);
	}
	
	/**
	 * Método usado para retornar todos os registros na tabela de categorias
	 * @return List<Categoria> contendo todos as categorias da tabela ou uma lista
	 * vazia caso não hajam registros.
	 */
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
	
	/**
	 * Método usado para deletar todos os registros da tabela de categorias.
	 */
	public void deletarTodosOsLivros() {
		categoriaRepository.deleteAll();
	}
	
	/**
	 * Método usado para deletar um dado registro na tabela identificado
	 * pelo seu ID
	 * @param id ID do registro que se deseja deletar
	 */
	public void deletarPeloId(Long id) {
		Optional<Categoria> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			categoriaRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método usado para saber se existem registros de Categoria no banco de dados
	 * @return True se existir pelo menos um registro.
	 */
	public boolean existemRegistros() {
		return categoriaRepository.count() > 0;
	}
}

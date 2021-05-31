package com.bookstore.com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.repository.LivroRepository;

/**
 * 
 * @author NPG (Grupo 2 - Projeto de DAC)
 * Classe responsável por fornecer os serviços de CRUD relativos a entidade Livro.
 * 
 */
@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	/**
	 * Método usado para salvar no banco de dados um novo registro de Livro
	 * @param livro Objeto que se deseja salvar no banco de dados.
	 */
	public void salvarLivro(Livro livro) {
		livroRepository.save(livro);
	}
	
	/**
	 * Método usado para retornar um Optional de Livro
	 * identificado pelo seu ID
	 * @param id ID do registro que se deseja obter da tabela
	 * @return O Optional de Livro que contém o registro do livro
	 * requisitado, caso seja encontrado.
	 */
	public Optional<Livro> recuperarPeloId(Long id) {
		return livroRepository.findById(id);
	}
	
	/**
	 * Método usado para retornar registros de livros ordenados e paginados.
	 * 5 registros por página.
	 * @param campoOrdenacao String que especifica a partir de qual atributo de Livro
	 * a página será ordenada.
	 * @param sortDirection Direção da ordenação, que pode ser ascendente ou descendente.
	 * @param numeroPagina Número da página que se deseja obter os registrs
	 * @param inEstoque Boolean que especifica se a busca deve ser realizada apenas entre os livros em estoque ou em geral.
	 * @return Uma página com os livros ordenados da forma especificada.
	 */
	public Page<Livro> listarLivros(String campoOrdenacao, Sort.Direction sortDirection, Integer numeroPagina, boolean inEstoque) {
		Sort sort = Sort.by(sortDirection, campoOrdenacao); // Ordenador que segue a direção e campos especificados
		
		Page<Livro> pagina = null;
		if(inEstoque) {
			// Página especificada pelo numPagina com no máximo 5 livros e ordenador criado anteriormente.
			// O metodo livrosEmEstoque retorna apenas os livros com estoque > 0.
			pagina = livroRepository.livrosEmEstoque(PageRequest.of(--numeroPagina, 5, sort));
		}else {
			// Página especificada pelo numPagina com no máximo 5 livros e ordenador criado anteriormente.
			pagina = livroRepository.findAll(PageRequest.of(--numeroPagina, 5, sort));
		}
		
		return pagina;
	}
	
	
	/**
	 * Método usado para retornar todos os registros na tabela de livros
	 * @return List<Livro> contendo todos os livros da tabela ou uma lista
	 * vazia caso não hajam registros.
	 */
	public List<Livro> listarLivros() {
		return livroRepository.findAll();
	}
	
	/**
	 * Método usado para deletar todos os registros da tabela de livros.
	 */
	public void deletarTodosOsLivros() {
		livroRepository.deleteAll();
	}
	
	/**
	 * Método usado para deletar um dado registro na tabela identificado
	 * pelo seu ID
	 * @param id ID do registro que se deseja deletar
	 */
	public void deletarPeloId(Long id) {
		Optional<Livro> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			livroRepository.delete(deletado.get());
		}
	}
	
	/**
	 * Método usado para atualizar um registro de um livro no banco de dados.
	 * Tem o comportamento igual ao metodo salvarLivro(Livro livro), mas foi criado
	 * para maior coesão e acessibilidade nesta classe de CRUD. 
	 * @param livro Livro que se deseja atualizar no banco de dados.
	 */
	public void atualizarLivro(Livro livro) {
		salvarLivro(livro);
	}
	
	/**
	 * Método usado para saber se existem registros de Livro no banco de dados
	 * @return True se existir pelo menos um registro.
	 */
	public boolean existemRegistros() {
		return livroRepository.count() > 0;
	}
	
}





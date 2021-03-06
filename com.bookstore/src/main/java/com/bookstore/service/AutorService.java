package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.bookstore.model.Autor;
import com.bookstore.repository.AutorRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos de CRUD sobre o Autor, que auxiliará na classe Livro
 *
 */
@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	/**
	 * Esse método salva (adicona ao banco de dados) um Autor de cada vez
	 * @param autor a ser adicionado ao banco
	 */
	public void salvarAutor(Autor autor) {
		
		autorRepository.save(autor);
	}
	
	/**
	 * Esse método recupera um Autor específico pelo seu ID
	 * @param id correspondente ao autor
	 * @return retorna um Optical contendo o autor encontrado
	 */
	public Optional<Autor> recuperarPeloId(Long id) {
		
		return autorRepository.findById(id);
	}
	
	/**
	 * Método usado para retornar registros de autores ordenados e paginados.
	 * @param campoOrdenacao String que especifica a partir de qual atributo de autor
	 * a página será ordenada.
	 * @param sortDirection Direção da ordenação, que pode ser ascendente ou descendente.
	 * @param numeroPagina Número da página que se deseja obter os registros
	 * @return Uma página com os autores ordenados da forma especificada.
	 */
	public Page<Autor> listarAutores(String campoOrdenacao, Sort.Direction sortDirection, Integer numeroPagina) {
		Sort sort = Sort.by(sortDirection, campoOrdenacao); // Ordenador que segue a direção e campos especificados
		
		Page<Autor> pagina = autorRepository.findAll(PageRequest.of(--numeroPagina, 10, sort));
		
		return pagina;
	}
	
	/**
	 * Esse método recupera uma lista de Autor pelo seu nome
	 * @param nome correspondente ao autor
	 * @return retorna uma lista contendo os autores encontrados
	 */
	public List<Autor> recuperarPeloNome(String nome) {
		
		return autorRepository.findByNome(nome);
	}
	
	/**
	 * Método responsável por recuperar uma lista de todos os autores
	 * @return lista de Autor
	 */
	public List<Autor> recuperarAutores(){
		
		return autorRepository.findAll();
	}
	
	/**
	 * Método responsável por deletar todos os autores do banco de dados
	 */
	public void deletarTodosAutores() {
		
		autorRepository.deleteAll();
	}
	
	/**
	 * Método responsável por deletar um Autor específico no 
	 * banco de dados
	 * @param id "Código" relacionado ao Autor específico
	 * a ser deletado
	 */
	public void deletarPeloId(Long id) {
		
		Optional<Autor> deletado = recuperarPeloId(id);
		
		if(deletado.isPresent()) { // Verifica se há um objeto no Optional
			autorRepository.deletarAutor(deletado.get().getID());
		}
	}
	
	/**
	 * Método responsável por atualizar um Autor
	 * @param autor a ser atualizado
	 */
	public void atualizarAutor(Autor autor) {
		salvarAutor(autor);
	}
	
	/**
	 * Método usado para saber se existem registros de Autor no banco de dados
	 * @return True se existir pelo menos um registro.
	 */
	public boolean existemRegistros() {
		
		return autorRepository.count() > 0;
	}
}

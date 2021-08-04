package com.bookstore.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bookstore.model.Autor;
import com.bookstore.model.Livro;
import com.bookstore.service.AutorService;


/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe Facade responsável por métodos relacionados a Autor. Esse padrão de projeto
 * foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadeAutor {

	@Autowired
	private AutorService autorService;
	
	/**
	 * Método responsável por criar um autor
	 * @param nome nome do autor
	 * @return retorna o Autor criado
	 * @throws Exception lança exceção caso ele não tenha sido cadastrado corretamente ao banco
	 */
	public Autor criarAutor(Autor autor) throws Exception {
		
//		Autor autor = new Autor(nome);
		autorService.salvarAutor(autor);
		
		return recuperarAutor(autor.getID());
		
	}
	
	/**
	 * Esse método remove um Autor pelo seu id caso o mesmo não esteja sendo utilizado em algum livro
	 * @param id id correspondente ao Autor
	 * @throws Exception lança exceção caso não seja encontrado o Autor
	 */
	public void removerAutor(Long id) throws Exception{
		recuperarAutor(id);
		autorService.deletarPeloId(id);
	}
	
	/**
	 * Esse método recupera um Autor pelo seu id
	 * @param id id correspondente ao Autor
	 * @return retorna o Autor encontrado
	 * @throws Exception lança exceção caso não seja encontrado o Autor
	 */
	public Autor recuperarAutor(Long id) throws Exception{
		
	    Optional<Autor> autor = autorService.recuperarPeloId(id);
		
	    if(autor.isPresent()) {
	    	return autor.get();
	    }
		
		throw new Exception("Autor não encontrado na base de dados, retorne a página de administração");
	}
	
	/**
	 * Método que retorna uma página contendo 9 instâncias de autores
	 * @param campoOrdenacao String que especifica a partir de qual atributo de autor
	 * a página será ordenada.
	 * @param sortDirection Direção da ordenação, que pode ser ascendente ou descendente.
	 * @param numeroPagina Número da página que se deseja obter os registros
	 * @return Uma página com os autores ordenados da forma especificada.
	 * @throws Exception lança excecao caso a pagina não contenha nenhum autor
	 */
	public Page<Autor> paginarAutores(String campoOrdenacao, Sort.Direction sortDirection, Integer numeroPagina) throws Exception{
		
		Page<Autor> pagTemp = autorService.listarAutores(campoOrdenacao, sortDirection, numeroPagina);
		if(!pagTemp.isEmpty()) {
			return pagTemp;			
		}
		throw new Exception("Nenhum Autor cadastrado");
	}
	
	/**
	 * Método responsável por recuperar os autores cadastrados no banco
	 * @return retorna a lista de autores cadastrados no banco
	 */
	public List<Autor> recuperarAutores(){
		
		return autorService.recuperarAutores();
	}
	
	/**
	 * Método retorna uma entidade com os atributos nulos, ou seja, vazios, para serem adicionados
	 * nos campos do fromulário
	 * @return retorna um Autor com os atributos vazios
	 */
	public Autor recuperarAutorNulo() {
	
		return new Autor("");
	}
	
	/**
	 * Esse método atualiza um Autor 
	 * @param autorDto autor com novos parâmetros para atualizar
	 * @param id do autor
	 * @throws Exception lança escessão caso não encontre o autor com id passado
	 */
	public void atualizarAutor(Autor autorDto, Long id) throws Exception {
		
		Autor autorUpdate = recuperarAutor(id);
				
		autorUpdate.setNome(autorDto.getNome());
		
		if(autorDto.getLivros().size() > 0) {
			autorUpdate.setLivros(autorDto.getLivros());
		}
		autorService.atualizarAutor(autorUpdate);
	}
	
	/**
	 * Método utilizado para adicionar um livro aos autores do mesmo
	 * @param autores Set de autores para adicionar o livro
	 * @param livro Livro a ser adicionado aos autores
	 * @throws Exception lança excecao caso algum autor nao esteja cadastrado no banco de dados
	 */
	public void salvarLivroAosAutores(Set<Autor> autores, Livro livro) throws Exception{
		
		for(Autor autor : autores) {
			
			autor.adicionarLivro(livro);
			atualizarAutor(autor, autor.getID());
		}
	}
	
	/**
	 * Método responsável por ciar a numeração da navegação entre as paginações 
	 * @param quantidadePaginas quantidade de páginas que existe 
	 * @param pagina pagina escolhida/clicada pelo usuario
	 * @return retorna um arrayList de Inteiros para gerar a nova paginação
	 */
	public List<Integer> criarPaginacao(Integer quantidadePaginas, Integer pagina) {
				
		List<Integer> paginas = new ArrayList<Integer>();
		
		if(pagina > 1) {
			paginas.add(pagina-1);
		}
		
		for(int i = pagina; i <= quantidadePaginas; i++) {
						
			paginas.add(i);
			
			if(paginas.size() == 5) {
				i = quantidadePaginas+1;
			}
		}
		
		return paginas;
		
	}
}

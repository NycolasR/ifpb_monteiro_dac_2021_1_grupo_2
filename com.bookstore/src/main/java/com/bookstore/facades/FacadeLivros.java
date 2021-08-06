package com.bookstore.facades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bookstore.model.Categoria;
import com.bookstore.model.Livro;
import com.bookstore.service.LivroService;

/**
 * 
 * @author NPG (Grupo 2)
 * Classe Façade usada para interfacear certas funcionalidades relativas a Livro.
 * Esse padrão de projeto foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 */
@Component
public class FacadeLivros {
	
	@Autowired
	private LivroService livroService;
	
	/**
	 * Método usado para criar um livro 
	 * @param livro livro a ser adicionado ao banco
	 * @return O registro de Livro pronto
	 * @throws Exception 
	 */
	public Long criarLivro(Livro livro) throws Exception {
		
//		Livro livro = new Livro(isbn, titulo, descricao, preco, edicao, anoPublicacao, qntdEstoque);
		livroService.salvarLivro(livro);
		return livro.getId();
	}
	
	/**
	 * Método usado para recuperar o registro de um Livro
	 * @param id ID do livro que se deseja obter
	 * @return O registro caso encontrado
	 * @throws Exception Lança exceção se o registro não for encontrado
	 */
	public Livro recuperarLivro(Long id) throws Exception {
		Optional<Livro> optional = livroService.recuperarPeloId(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("Livro não encontrado na base de dados, retorne a página de administração");
	}

	/**
	 * Método que retorna uma página contendo 9 instâncias de livros
	 * @param campoOrdenacao String que especifica a partir de qual atributo de Livro
	 * a página será ordenada.
	 * @param sortDirection Direção da ordenação, que pode ser ascendente ou descendente.
	 * @param numeroPagina Número da página que se deseja obter os registros
	 * @param inEstoque Boolean que especifica se a busca deve ser realizada apenas entre os livros em estoque ou em geral.
	 * @param categorias id das categorias para filtragem
	 * @param stringDeBusca string de busca relaconada ao titulo do livro que se deseja buscar
	 * @return Uma página com os livros ordenados da forma especificada.
	 * @throws Exception lança excecao caso a pagina não contenha nenhum livro
	 */
	public Page<Livro> paginarLivros(String campoOrdenacao, Sort.Direction sortDirection, Integer numeroPagina, 
			boolean inEstoque, List<Integer> categorias, String stringDeBusca) throws Exception{
		
		Page<Livro> pagTemp = null;
		
		if(!stringDeBusca.equals("")) {
			
			pagTemp = livroService.listarLivrosBuscados(stringDeBusca, numeroPagina);
			
		}else if(categorias != null && categorias.get(0) != 0) {
			
			pagTemp = livroService.listarLivrosFiltrados(categorias, numeroPagina);
			
		}else {
			pagTemp = livroService.listarLivros(campoOrdenacao, sortDirection, numeroPagina, inEstoque);
		}

		if(!pagTemp.isEmpty()) {
			return pagTemp;			
		}
		throw new Exception("Nenhum Livro cadastrado");
	}
	
	/**
	 * Método responsável por recuperar os livros cadastrados no banco
	 * @return retorna a lista de livros cadastrados no banco
	 */
	public List<Livro> recuperarLivros(){
		
		return livroService.listarLivros();
	}
	
	/**
	 * Método retorna uma entidade com os atributos nulos, ou seja, vazios, para serem adicionados
	 * nos campos do fromulário
	 * @return retorna um Livro com os atributos vazios
	 */
	public Livro recuperarLivroNulo() {
		
		return new Livro("", "", "", new BigDecimal(0.00), 1, 1, 0);
	}
	
	/**
	 * Método usado para deletar um registro de livro
	 * @param livro Livro que está para ser deletado
	 * @throws Exception lança excessão caso o id não corresponda a algum livro do banco 
	 */
	public void deletarLivro(Long id) throws Exception {
		recuperarLivro(id);
		livroService.deletarPeloId(id);
	}

	/**
	 * Método usado para atualizar um registro de livro
	 * @param livro Livro com atributos atualizados
	 * @param id do livro
	 * @throws Exception lança excessão caso o id não corresponda a algum livro do banco 
	 */
	public void atualizarLivro(Livro livroDto, Long id) throws Exception {
		
		Livro livroUpdate = recuperarLivro(id);
		
		livroUpdate.setIsbn(livroDto.getIsbn());
		livroUpdate.setUrlImagemCapa(livroDto.getUrlImagemCapa());
		livroUpdate.setTitulo(livroDto.getTitulo());;
		livroUpdate.setDescricao(livroDto.getDescricao());
		livroUpdate.setPreco(livroDto.getPreco());
		livroUpdate.setEdicao(livroDto.getEdicao());
		livroUpdate.setAnoPublicacao(livroDto.getAnoPublicacao());
		livroUpdate.setCategorias(livroDto.getCategorias());
		livroUpdate.setAutores(livroDto.getAutores());
		livroUpdate.setQuantidadeEmEstoque(livroDto.getQuantidadeEmEstoque());
		
		livroService.atualizarLivro(livroUpdate);
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
	
	public boolean livroExisteNoBd(Long id) {
		if(id == null)
			return false;
		
		Optional<Livro> optional = livroService.recuperarPeloId(id);
		return optional.isPresent();
	}
}

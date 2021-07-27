package com.bookstore.facades;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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
	 */
	public Long criarLivro(Livro livro) {
		
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
		
		throw new Exception("[ERRO] Livro não encontrado na base de dados.");
	}

	/**
	 * Nyk depois escreve aqui o que esse método faz
	 * @param campoOrdenacao
	 * @param sortDirection
	 * @param numeroPagina
	 * @param inEstoque
	 * @return
	 * @throws Exception
	 */
	public Page<Livro> paginarLivros(String campoOrdenacao, Sort.Direction sortDirection, Integer numeroPagina, boolean inEstoque) throws Exception{
		Page<Livro> pagTemp = livroService.listarLivros(campoOrdenacao, sortDirection, numeroPagina, inEstoque);
		if(!pagTemp.isEmpty()) {
			return pagTemp;			
		}
		throw new Exception("[ERRO] Nenhum Livro cadastrado");
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
		
		return new Livro(0L, "", "", new BigDecimal(0.00), 1, 1, 0);
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
		livroUpdate.setImagemCapa(livroDto.getImagemCapa());
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
	
	
	
	
	
	
	
}

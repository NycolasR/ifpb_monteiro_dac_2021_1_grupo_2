package com.bookstore.com.bookstore.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ESTOQUE")
public class Estoque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ADICIONADO_ESTOQUE_FK")
	private Set<Livro> livrosAdicionados = new LinkedHashSet<Livro>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "EXCLUIDO_ESTOQUE_FK")
	private Set<Livro> livrosExcluidos = new LinkedHashSet<Livro>();
	
	public void adicionarLivro(Livro livro) {
		this.livrosAdicionados.add(livro);
		
		try {
			this.livrosExcluidos.remove(encontrarLivroEmColecao(livrosExcluidos, livro));
		} catch (Exception e) {
			// Se o livro não for encontrado nos excluídos, não há problema.
		}
	}
	
	public void excluirLivro(Livro livro) throws Exception {
		Livro livroRecuperado = encontrarLivroEmColecao(livrosAdicionados, livro);
		livrosAdicionados.remove(livroRecuperado);
		livrosExcluidos.add(livroRecuperado);
	}
	
	/**
	 * Método usado para recuperar um livro de uma coleção desta classe
	 * @param colecao Set de livros que pode ser os adicionados ou os excluídos
	 * @param livro Livros que se deseja recuperar da coleção
	 * @return O Livro já encontrado
	 * @throws Exception Lança exceção caso o livro não seja encontrado
	 */
	private Livro encontrarLivroEmColecao(Set<Livro> colecao, Livro livro) throws Exception {
		Livro[] livrosArr = colecao.toArray(new Livro[colecao.size()]);
		
		for (int i = 0; i < livrosArr.length; i++)
			if(livrosArr[i].equals(livro))
				return livrosArr[i];
		
		throw new Exception("[ERRO] Livro não encontrado na coleção");
	}

	public Set<Livro> getItensAdicionados() {
		return livrosAdicionados;
	}

	public void setItensAdicionados(Set<Livro> itensAdicionados) {
		this.livrosAdicionados = itensAdicionados;
	}

	public Set<Livro> getItensExcluidos() {
		return livrosExcluidos;
	}

	public void setItensExcluidos(Set<Livro> itensExcluidos) {
		this.livrosExcluidos = itensExcluidos;
	}
	
}

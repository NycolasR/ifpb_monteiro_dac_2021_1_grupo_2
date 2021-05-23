package com.bookstore.com.bookstore.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.ImageIcon;

@Entity
@Table(name = "TB_LIVRO")
public class Livro {

	@Id
	@Column(name = "ISBN")
	private Long isbn;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "PRECO")
	private Float preco;

	//	private ImageIcon imagemCapa;

	@Column(name = "EDICAO")
	private Integer edicao;

	@Column(name = "ANO_PUBLICACAO")
	private LocalDate anoPublicacao;

	//	@Enumerated(EnumType.STRING)
	//	private List<Categoria> categorias;

	public Livro(Long isbn, String titulo, String descricao, Float preco, Integer edicao, LocalDate anoPublicacao) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
	}
	
	public Livro() {
		
	}

	public Long getISBN() {
		return isbn;
	}
	public void setISBN(Long iSBN) {
		isbn = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	//	public Float getItensAdicionados() {
	//		return itensAdicionados;
	//	}
	//	public void setItensAdicionados(Float itensAdicionados) {
	//		this.itensAdicionados = itensAdicionados;
	//	}
	//	
	//	public ImageIcon getImagemCapa() {
	//		return imagemCapa;
	//	}
	//	public void setImagemCapa(ImageIcon imagemCapa) {
	//		this.imagemCapa = imagemCapa;
	//	}

	public Integer getEdicao() {
		return edicao;
	}
	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public LocalDate getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(LocalDate anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	//	public List<Categoria> getCategorias() {
	//		return categorias;
	//	}
	//	public void setCategorias(List<Categoria> categorias) {
	//		this.categorias = categorias;
	//	}


	public boolean equals(Livro livro) {
		return this.isbn.equals(livro.getISBN());
	}

	@Override
	public String toString() {
		return "Dados do Livro " + titulo + ": "
				+ "\n	ISBN: " + isbn
				+ "\n	Descrição: " + descricao
				+ "\n	Preço: R$" + preco
				+ "\n	Edição: " + edicao
				+ "\n	Ano de Publicação: " + anoPublicacao;
	}
}

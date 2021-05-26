package com.bookstore.com.bookstore.model;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LIVRO")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "QNTD_ESTOQUE")
	private Integer quantidadeEmEstoque;
	
	@Column(name = "ISBN")
	private Long isbn;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "PRECO")
	private BigDecimal preco;

	@Lob
	@Column(name="IMAGEM_CAPA", columnDefinition="mediumblob")
	private byte[] imagemCapa;

	@Column(name = "EDICAO")
	private Integer edicao;

	@Column(name = "ANO_PUBLICACAO")
	private Integer anoPublicacao;
	
	@ElementCollection(fetch = FetchType.LAZY, targetClass = Categoria.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORIA", length = 30)
	@JoinTable(name = "TB_CATEGORIA")
	private Set<Categoria> categorias = new LinkedHashSet<Categoria>();

	@ManyToMany(mappedBy = "livros")
	private Set<Autor> autores = new LinkedHashSet<Autor>();

	public Livro(
			Long isbn,
			String titulo,
			String descricao,
			BigDecimal preco,
			Integer edicao,
			Integer anoPublicacao,
			Integer quantidadeEmEstoque) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	
	public Livro() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public byte[] getImagemCapa() {
		return imagemCapa;
	}
	public void setImagemCapa(byte[] imagemCapa) {
		this.imagemCapa = imagemCapa;
	}
	
	public void setImageFile(File imgFile) {
		byte[] img = new byte[(int) imgFile.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(imgFile);
			fileInputStream.read(img);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setImagemCapa(img);
	}

	public Integer getEdicao() {
		return edicao;
	}
	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Set<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public boolean equals(Livro livro) {
		if (this == livro)
			return true;

		if (livro == null)
			return false;

		if (getClass() != livro.getClass())
			return false;

		Livro novoLivro = (Livro) livro;
		if ((id == null && novoLivro.getId() != null) || !id.equals(novoLivro.getId()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "\n\nDados do Livro " + titulo + ": "
				+ "\nISBN: " + isbn
				+ "\nDescrição: " + descricao
				+ "\nPreço: R$" + preco
				+ "\nEdição: " + edicao
				+ "\nAno de Publicação: " + anoPublicacao
				+ "\nQuantidade disponível no estoque: " + quantidadeEmEstoque + " unidades";
	}
}

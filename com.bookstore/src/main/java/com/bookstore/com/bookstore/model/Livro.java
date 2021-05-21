package com.bookstore.com.bookstore.model;

import java.time.LocalDate;
import java.util.List;

import javax.swing.ImageIcon;

public class Livro {

	private Long ISBN;
	private String titulo;
	private String descricao;
	private Float itensAdicionados;
	private ImageIcon imagemCapa;
	private Integer edicao;
	private LocalDate anoPublicacao;
	private List<Categoria> categorias;
	
	
	public Long getISBN() {
		return ISBN;
	}
	public void setISBN(Long iSBN) {
		ISBN = iSBN;
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
	
	public Float getItensAdicionados() {
		return itensAdicionados;
	}
	public void setItensAdicionados(Float itensAdicionados) {
		this.itensAdicionados = itensAdicionados;
	}
	
	public ImageIcon getImagemCapa() {
		return imagemCapa;
	}
	public void setImagemCapa(ImageIcon imagemCapa) {
		this.imagemCapa = imagemCapa;
	}
	
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
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	

}

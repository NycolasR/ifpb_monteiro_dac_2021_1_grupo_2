package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Livro {

	private long ISBN;
	private String titulo;
	private String descricao;
	private float itensAdicionados;
	private ImageIcon imagemCapa;
	private int edicao;
	private LocalDate anoPublicacao;
	private ArrayList<Categoria> categorias;
	
	
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
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
	public float getItensAdicionados() {
		return itensAdicionados;
	}
	public void setItensAdicionados(float itensAdicionados) {
		this.itensAdicionados = itensAdicionados;
	}
	public ImageIcon getImagemCapa() {
		return imagemCapa;
	}
	public void setImagemCapa(ImageIcon imagemCapa) {
		this.imagemCapa = imagemCapa;
	}
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public LocalDate getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(LocalDate anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
	

}

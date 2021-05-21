package com.bookstore.com.bookstore.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ESTOQUE")
public class Estoque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	private static Estoque estoque;
//	
//	private Estoque() {	
//		
//	}
//	
//	public static Estoque getInstance() {
//		if(estoque == null) {
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory("TB_ESTOQUE");
//			estoque = (Estoque) emf.createEntityManager();
//		}
//		
//		return estoque;
//	}
	
	@Column(name = "NOME")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

//	private List<Livro> itensAdicionados;
//	private List<Livro> itensExcluidos;
//
//	
//	public List<Livro> getItensAdicionados() {
//		return itensAdicionados;
//	}
//
//	public void setItensAdicionados(List<Livro> itensAdicionados) {
//		this.itensAdicionados = itensAdicionados;
//	}
//
//	public List<Livro> getItensExcluidos() {
//		return itensExcluidos;
//	}
//
//	public void setItensExcluidos(List<Livro> itensExcluidos) {
//		this.itensExcluidos = itensExcluidos;
//	}
	
}

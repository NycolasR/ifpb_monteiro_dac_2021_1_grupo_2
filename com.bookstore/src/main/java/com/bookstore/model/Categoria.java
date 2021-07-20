package com.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe relativa a entidade Categoria
 *
 */
@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public Categoria() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean equals(Categoria categoria) {
		if (this == categoria)
			return true;

		if (categoria == null)
			return false;

		if (getClass() != categoria.getClass())
			return false;

		Categoria novaCategoria = (Categoria) categoria;
		if ((id == null && novaCategoria.getId() != null) || !id.equals(novaCategoria.getId()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	/*
	 * INFORMATICA, ROMANCE, AVENTURA, INFANTIL, TERROR, ENGENHARIA, CLASSICO,
	 * FANTASIA, FICCAO_CIENTIFICA, DISTOPIA, DRAMA, BIOGRAFIA;
	 */
}

package com.bookstore.com.bookstore.model;

public class Autor {

	private String nome;
	private Long ID;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public boolean equals(Autor autor) {
		return autor.getID() == ID;
	}
	

}

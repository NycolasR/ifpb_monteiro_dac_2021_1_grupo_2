package com.bookstore.com.bookstore.model;

public class Usuario {

	private long ID;
	private String nome;
	private String email;
	private String senha;
	private boolean isAdmi;
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmi() {
		return isAdmi;
	}

	public void setAdmi(boolean isAdmi) {
		this.isAdmi = isAdmi;
	}

}

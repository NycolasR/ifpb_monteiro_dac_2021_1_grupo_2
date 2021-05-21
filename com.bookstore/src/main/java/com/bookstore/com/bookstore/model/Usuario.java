package com.bookstore.com.bookstore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "ADMINISTRADOR")
	private boolean isAdmin;
	
	private List<Endereco> enderecos;
	
	public Long getID() {
		return id;
	}

	public void setID(Long iD) {
		id = iD;
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
		return isAdmin;
	}

	public void setAdmi(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Usuario newUser = (Usuario) obj;
		if ((id == null && newUser.id != null) || !id.equals(newUser.id))
			return false;
		
		return true;
	
	}
	
	@Override
	public String toString() {
		return "Usuario: {"
			+ "\n	id: " + id + ","
			+ "\n	nome: " + nome + ","
			+ "\n	email: " + email + ", "
			+ "\n	senha: " + senha + ", "
			+ "\n	administrador: " + isAdmin + ", "
			+ "\n}";
	}
	
	
}

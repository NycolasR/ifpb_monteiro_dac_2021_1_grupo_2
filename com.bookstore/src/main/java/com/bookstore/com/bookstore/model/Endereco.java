package com.bookstore.com.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "RUA")
	private String rua;
	
	@Column(name = "NUMERO")
	private String numero;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "UF")
	private String UF;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	@Column(name = "CEP")
	private Integer CEP;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Integer getCEP() {
		return CEP;
	}
	public void setCEP(Integer cEP) {
		CEP = cEP;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Endereco newEndereco = (Endereco) obj;
		if ((id == null && newEndereco.id != null) || !id.equals(newEndereco.id))
			return false;
		
		return true;
	
	}
	
	@Override
	public String toString() {
		return "Endereço: {"
			+ "\n	id: " + id + ","
			+ "\n	Rua: " + rua + ","
			+ "\n	Numero: " + numero + ", "
			+ "\n	Bairro: " + bairro + ", "
			+ "\n	UF: " + UF + ", "
			+ "\n	Cidade: " + cidade + ", "
			+ "\n	Complemento: " + complemento + ", "
			+ "\n	CEP: " + CEP + ", "
			+ "\n}";
	}
	
}

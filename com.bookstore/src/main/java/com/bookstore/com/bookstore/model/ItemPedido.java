package com.bookstore.com.bookstore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@Column(name = "LIVRO_FK")
	private Livro livro;
	
	public ItemPedido(Integer quantidade, Livro livro) {
		this.quantidade = quantidade;
		this.livro = livro;
	}
	
	public ItemPedido() {}

	public Long getID() {
		return ID;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	@Override
	public String toString() {
		
		return "Livro :" + livro.getTitulo() +"\nID :" + ID + "\nQuantidade :" + quantidade ; 
	}
	
	public boolean equals(ItemPedido itemPedido) {
		return itemPedido.getID().equals(ID);
	}
	
	
}

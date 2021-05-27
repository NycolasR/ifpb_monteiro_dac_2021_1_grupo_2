package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe visa facilitar a relação entre as classes Pedido e Livro
 *
 */

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
	
	@Column(name = "VALOR_TOTAL")
	private BigDecimal valorTotalItemPedido;
	
	@ManyToOne
	@JoinColumn(name = "LIVRO_FK")
	private Livro livro;
	
	public ItemPedido(Integer quantidade, Livro livro) {
		this.quantidade = quantidade;
		this.livro = livro;
		atualizarValorTotal();
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
		atualizarValorTotal();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;		
		atualizarValorTotal();
	}
		
	public BigDecimal getValorTotalItemPedido() {
		return valorTotalItemPedido;
	}
	
	public BigDecimal getValorIndividual() {
		return livro.getPreco();
	}
	
	private void atualizarValorTotal() {
		this.valorTotalItemPedido = valorTotalItemPedido.multiply(new BigDecimal(quantidade));
	}

	@Override
	public String toString() {
		
		return "Livro :" + livro.getTitulo() +"\nID :" + ID + "\nQuantidade :" + quantidade ; 
	}
	
	public boolean equals(ItemPedido itemPedido) {
		return itemPedido.getID().equals(ID);
	}
	
	
}
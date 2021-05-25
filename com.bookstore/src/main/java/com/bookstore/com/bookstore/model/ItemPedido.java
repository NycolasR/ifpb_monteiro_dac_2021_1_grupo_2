package com.bookstore.com.bookstore.model;

public class ItemPedido {

	private Long ID;
	private Integer quantidade;
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

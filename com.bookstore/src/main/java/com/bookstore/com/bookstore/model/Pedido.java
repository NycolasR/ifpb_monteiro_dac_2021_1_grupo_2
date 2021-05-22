package com.bookstore.com.bookstore.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

	private Long ID;
	private LocalDate dataCriacao;
	private LocalDate dataFechamento;
	private Integer qntdItens;
	private Float total;
	private String status_pedido;
	private FormaPagamento formaPagamento;
	private ArrayList<Livro> livros;
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public LocalDate getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Integer getQntdItens() {
		return qntdItens;
	}
	public void setQntdItens(Integer qntdItens) {
		this.qntdItens = qntdItens;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getStatus_pedido() {
		return status_pedido;
	}
	public void setStatus_pedido(String status_pedido) {
		this.status_pedido = status_pedido;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public ArrayList<Livro> getLivros() {
		return livros;
	}
	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}
}

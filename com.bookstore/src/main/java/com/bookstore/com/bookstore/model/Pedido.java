package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * a classe Contexto.
 *
 */
public class Pedido {

	private Long ID;
	private Usuario usuario;
	private LocalDate dataCriacao;
	private LocalDate dataFechamento;
	private Integer qntdItens;
	private BigDecimal valorItensTotal;
	private String statusPedido;
	private FormaPagamento formaPagamento;
	private Set<Livro> livros = new LinkedHashSet<Livro>();
	
	/**
	 * Método responsável por adicionar um livro de cada vez ao pedido.
	 * @param livro que o usuário deseja comprar.
	 */
	public void adicionarLivro(Livro livro) {
		livros.add(livro);
		this.valorItensTotal = livro.getPreco().add(valorItensTotal);
	}
	
	/**
	 * Esse método remove um livro que já foi adicionado ao pedido.
	 * @param ISBN é o "código" do livro a ser removido.
	 */
	public void removerLivro(Long ISBN) {
		
		Iterator<Livro> livrosIterator = livros.iterator();
		while(livrosIterator.hasNext()) {
			
			Livro livro = livrosIterator.next();
			if(livro.getISBN()== ISBN) {
				livros.remove(livro);
			}
		}
	}
	
	/**
	 * O método a baixo cria uma lista dos itens individuais e seus 
	 * respectivos preços que foram adiconados ao pedido.
	 * @return retorna uma lista dos itens individuais e seus respectivos preços
	 */
	public StringBuffer mostrarValoresItensIndividuais() {
		
		StringBuffer valoresIndividuais = new StringBuffer();
		
		for(Livro livro: livros) {
			
			valoresIndividuais.append(livro.getTitulo() +" :"+ livro.getPreco());
		}
		
		return valoresIndividuais;
	}
	
	/**
	 * Esse método cria uam String com a informação de um item específico e seu
	 * respectivo preço.
	 * @param ISBN "Códio" do livro a ser buscado dentro do pedido
	 * @return retorna as informações do livro encontrado dentro do pedido.
	 */
	public String mostrarValoresDeUmItemIndividual(Long ISBN) {
		
		String valorIndividual = new String();
		
		for(Livro livro: livros) {
			
			if(livro.getISBN() == ISBN) {
				valorIndividual = livro.getTitulo() +" :"+ livro.getPreco();
				break;
			}
		}
		return valorIndividual;
	}
	
	/*
	 * Os métodos a baixo são de get e set em relação aos atributos da classe.
	 * 
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
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
	public BigDecimal getTotal() {
		return valorItensTotal;
	}
	public void setTotal(BigDecimal total) {
		this.valorItensTotal = total;
	}
	public String getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Set<Livro> getLivros() {
		return livros;
	}
	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
	
	@Override
	public String toString() {
		return "Dados do Pedido " + ID + ": "
				+ "\n	Nome Cliente: " + usuario.getNome()
				+ "\n	Quantidade de Itens: " + qntdItens
				+ "\n	Preço Total: R$" + valorItensTotal
				+ "\n	Forma de Pagamento: " + formaPagamento
				+ "\n	Endereço de entrega: " + usuario.getEnderecos().get(0);
	}
	
	public boolean equals(Pedido pedido) {
		return pedido.getID() == ID;
	}
}

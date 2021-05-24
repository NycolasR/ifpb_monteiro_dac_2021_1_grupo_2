package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * a classe Contexto.
 *
 */
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
	
	@ManyToOne
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CRIACAO")
	private Date dataCriacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FECHAMENTO")
	private Date dataFechamento;
	
	@Column(name = "QUANTIDADE_ITENS")
	private Integer qntdItens;
	
	@Column(name = "VALOR_ITENS_TOTAL")
	private BigDecimal valorItensTotal;
	
	@Column(name = "STATUS_PEDIDO")
	private String statusPedido;
	
	@Column(name = "LOCAL_ENTREGA")
	private String localDeEntrega;

	@OneToOne(cascade = CascadeType.ALL)
	private FormaPagamento formaPagamento; 
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "PEDIDO_FK")
	private Set<Livro> livros = new LinkedHashSet<Livro>(); 
	
	public Pedido() {
		
		dataCriacao = Date.from(Instant.now());
		statusPedido = "Não Finalizado";
		qntdItens = 0;
	}
	
	
	/**
	 * Método responsável por adicionar um livro de cada vez ao pedido.
	 * @param livro que o usuário deseja comprar.
	 */
	public void adicionarLivro(Livro livro) {
		
		livros.add(livro);
		qntdItens += 1;
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
				
				valorItensTotal = valorItensTotal.subtract(livro.getPreco()); //diminuir o valor do livro removido, do valor total
				livros.remove(livro);
				qntdItens -= 1;
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
	public String getLocalDeEntrega() {
		return localDeEntrega;
	}
	public void setLocalDeEntrega(String localDeEntrega) {
		this.localDeEntrega = localDeEntrega;
	}
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
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Integer getQntdItens() {
		return qntdItens;
	}
	public void setQntdItens(Integer qntdItens) {
		this.qntdItens = qntdItens;
	}
	public BigDecimal getvalorItensTotal() {
		return valorItensTotal;
	}
	public void setvalorItensTotal(BigDecimal valorItensTotal) {
		this.valorItensTotal = valorItensTotal;
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
				+ "\n	Endereço de entrega: " + localDeEntrega;
	}
	
	public boolean equals(Pedido pedido) {
		return pedido.getID().equals(ID);
	}
}

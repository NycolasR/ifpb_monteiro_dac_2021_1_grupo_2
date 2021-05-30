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

	@ManyToOne
	@JoinColumn(name = "FORMA_PAGAMENTO_FK")
	private FormaPagamento formaPagamento; 
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "PEDIDO_FK")
	private Set<ItemPedido> itensPedidos = new LinkedHashSet<ItemPedido>(); 
	
	public Pedido() {
		
		dataCriacao = Date.from(Instant.now());
		statusPedido = "Não Finalizado";
		qntdItens = 0;
		valorItensTotal = new BigDecimal(0);
	}
	
	
	/**
	 * Método responsável por adicionar um itemPedido de cada vez ao pedido.
	 * @param itemPedido que o usuário deseja adicionar.
	 */
	public void adicionarItemPedido(ItemPedido itemPedido) {
		
		itensPedidos.add(itemPedido);
		qntdItens += itemPedido.getQuantidade();
		this.valorItensTotal = valorItensTotal.add(itemPedido.getValorTotalItemPedido());
	}
	
	/**
	 * Esse método remove um itemPedido que já foi adicionado ao pedido.
	 * @param ID é o "código" do itemPedido a ser removido.
	 */
	public void removerItemPedido(Long ID) {
		
		Iterator<ItemPedido> itensPedidosIterator = itensPedidos.iterator();
		while(itensPedidosIterator.hasNext()) {
			
			ItemPedido itemPedido = itensPedidosIterator.next();
			if(itemPedido.getID()== ID) {
				
				valorItensTotal = valorItensTotal.subtract(itemPedido.getValorTotalItemPedido()); //diminuir o valor do livro removido, do valor total
				qntdItens -= itemPedido.getQuantidade();
				itensPedidos.remove(itemPedido);
				
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
		
		for(ItemPedido itemPedido: itensPedidos) {
			
			valoresIndividuais.append(itemPedido.getLivro().getTitulo() +" :"+ itemPedido.getValorIndividual());
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
		
		for(ItemPedido itemPedido: itensPedidos) {
			
			if(itemPedido.getLivro().getISBN() == ISBN) {
				valorIndividual = itemPedido.getLivro().getTitulo() +" :"+ itemPedido.getValorIndividual();
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
	public Set<ItemPedido> getItemPedidos() {
		return itensPedidos;
	}
	public void setLivros(Set<ItemPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
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

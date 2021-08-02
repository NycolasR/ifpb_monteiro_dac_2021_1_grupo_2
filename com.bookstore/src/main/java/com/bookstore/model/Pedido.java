package com.bookstore.model;

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
	
	@Column(name = "VALOR_ITENS_TOTAL", columnDefinition = "DECIMAL(7,2)")
	private BigDecimal valorItensTotal;
	
	@Column(name = "STATUS_PEDIDO")
	private String statusPedido;
	
	@ManyToOne
	@JoinColumn(name = "ENDERECO_FK")
	private Endereco localDeEntrega;

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
	public String mostrarValoresDeUmItemIndividual(Long isbn) {
		
		String valorIndividual = new String();
		
		for(ItemPedido itemPedido: itensPedidos) {
			
			if(itemPedido.getLivro().getIsbn().equals(isbn)) {
				valorIndividual = itemPedido.getLivro().getTitulo() +" :"+ itemPedido.getValorIndividual();
				break;
			}
		}
		return valorIndividual;
	}
	
	/**
	 * Método responsável por recuperar um ItemPedido específico 
	 * @param isbn "código" do livro
	 * @return retorna um itemPedido contendo o livro correspondente
	 * @throws Exception lança uma exceção caso não encontre um itemPedido contendo o livro correspondente
	 */
	public ItemPedido recuperarItemPedido(Long isbn) throws Exception{
		
		for(ItemPedido itemPedido: itensPedidos) {
			
			if(itemPedido.getLivro().getIsbn().equals(isbn)) {
				return itemPedido;
			}
		}
		throw new Exception("[ERRO] Livro Inexistente");
		
	}
	
	/**
	 * Esse método verifica se a quantidade do estoque atende a quantidade
	 * solicitada no pedido
	 * @throws Exception lança exceção caso a quantidade pedida seja 
	 * maior que a quantidade em estoque.
	 */
	public void verificarEstoque() throws Exception{
		
		Iterator<ItemPedido> itensPedidosIterator = itensPedidos.iterator();
		while(itensPedidosIterator.hasNext()) {
			
			ItemPedido itemPedido = itensPedidosIterator.next();
			
			
			if(itemPedido.getLivro().getQuantidadeEmEstoque() < itemPedido.getQuantidade()) {
				
				throw new Exception("[ERRO] Estoque não suficiente");	
			}
		}
	}
		
	/*
	 * Os métodos a baixo são de get e set em relação aos atributos da classe.
	 * 
	 */
	public Endereco getLocalDeEntrega() {
		return localDeEntrega;
	}
	public void setLocalDeEntrega(Endereco localDeEntrega) {
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
	public BigDecimal getValorItensTotal() {
		return valorItensTotal;
	}
	public void setValorItensTotal(BigDecimal valorItensTotal) {
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
	public Set<ItemPedido> getItensPedidos() {
		return itensPedidos;
	}
	public void setItensPedidos(Set<ItemPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}
	
	@Override
	public String toString() {
		return "Dados do Pedido " + ID + ": "
				+ "\n	Nome Cliente: " + "aqui vai o nome do usuario"
				+ "\n	Quantidade de Itens: " + qntdItens
				+ "\n	Preço Total: R$" + valorItensTotal
				+ "\n	Forma de Pagamento: " + formaPagamento
				+ "\n	Endereço de entrega: " + localDeEntrega;
	}
	
	public boolean equals(Pedido pedido) {
		return pedido.getID().equals(ID);
	}
}

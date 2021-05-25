package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_REGISTRO_VENDAS")
public class RegistroVendas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "DATA_VENDA")
	private LocalDate dataVenda;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LIVRO_FK", nullable = false)
	private Livro registroVendas;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENTE_FK", nullable = false)
	private Usuario cliente;
	
	@Column(name = "QNTD_ITENS_VENDIDOS")
	private Integer quantidadeItensVendidos;
	
	@Column(name = "VALOR_PAGO")
	private BigDecimal valorPago;

	public RegistroVendas(
			LocalDate dataVenda,
			Livro livro,
			Usuario cliente,
			Integer quantidadeItensVendidos,
			BigDecimal valorPago) {
		this.dataVenda = dataVenda;
		this.registroVendas = livro;
		this.cliente = cliente;
		this.quantidadeItensVendidos = quantidadeItensVendidos;
		this.valorPago = valorPago;
	}
	
	public RegistroVendas() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Livro getLivro() {
		return registroVendas;
	}

	public void setLivro(Livro livro) {
		this.registroVendas = livro;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Integer getQuantidadeItensVendidos() {
		return quantidadeItensVendidos;
	}

	public void setQuantidadeItensVendidos(Integer quantidadeItensVendidos) {
		this.quantidadeItensVendidos = quantidadeItensVendidos;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	public boolean equals(RegistroVendas registroVendas) {
		if (this == registroVendas)
			return true;

		if (registroVendas == null)
			return false;

		if (getClass() != registroVendas.getClass())
			return false;

		RegistroVendas novoRegistro = (RegistroVendas) registroVendas;
		if ((id == null && novoRegistro.getId() != null) || !id.equals(novoRegistro.getId()))
			return false;

		return true;
	}
}








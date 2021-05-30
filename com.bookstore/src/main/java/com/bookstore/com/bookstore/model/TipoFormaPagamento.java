package com.bookstore.com.bookstore.model;

public enum TipoFormaPagamento {

	CARTAO("Cartão"),
	BOLETO("Boleto"),
	PIX("Pix"),
	TRANFERENCIA_BANCARIA("Transferência Bancária");

	private final String tipo;
	
	TipoFormaPagamento(String tipo) {
		
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	
}

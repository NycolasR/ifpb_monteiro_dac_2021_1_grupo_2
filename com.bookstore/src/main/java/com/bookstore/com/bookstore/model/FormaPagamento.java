package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * a classe Interface Strategy. 
 *
 */
public class FormaPagamento {
	
	private Long ID;
	private String nomeTipo; // nomeTipo representa o tipo que cada subtipificação de FormaPagamento pode ser.

	public Long getID() {
		return ID;
	}

	public String getNome() {
		return nomeTipo;
	}
	
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	
	/**
	 * Esse método será sobreescrito em todas as subtipificações da classe FormaPagamento (Interface Strategy).
	 * @param valor será o valor a ser pago ao se realizar o pagamento.
	 */
	public void realizarPagamento(BigDecimal valor) {}
	
	@Override
	public String toString() {
		return "Forma de pagamento: " + nomeTipo;
	}
	
	public boolean equals(FormaPagamento formaPagamento) {
		return formaPagamento.ID == ID;
	}

}

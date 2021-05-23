package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * uma classe concreta Strategy, ou seja, que herda da super classe FormaPagamento (Interface Strategy)
 *
 */
public class Boleto extends FormaPagamento{

	public Boleto() {
		setNomeTipo("Boleto"); // O atributo nomeTipo da superclasse (FormaPagamento) é adicionado correspondendo ao tipo da classe que à herda (Boleto).
	}
	
	/**
	 * Método da superclasse, sobreescrito na subclasse.
	 */
	@Override
	public void realizarPagamento(BigDecimal valor) {
		System.out.println("pagamento por Boleto");
	}
	
}
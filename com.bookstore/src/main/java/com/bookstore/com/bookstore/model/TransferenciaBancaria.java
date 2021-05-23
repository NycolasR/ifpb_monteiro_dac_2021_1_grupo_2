package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * uma classe concreta Strategy, ou seja, que herda da super classe FormaPagamento (Interface Strategy)
 *
 */
@DiscriminatorValue("Transferência Bancária")
public class TransferenciaBancaria extends FormaPagamento{

	public TransferenciaBancaria() {
		setNomeTipo("Transferência Bancária"); // O atributo nomeTipo da superclasse (FormaPagamento) é 
											   // adicionado correspondendo ao tipo da classe que à herda (Transferência Bancária).
	}
	
	/**
	 * Método da superclasse, sobreescrito na subclasse.
	 */
	@Override
	public void realizarPagamento(BigDecimal valor) {
		System.out.println("Pagamento por Transferência Bancária");
	}
}

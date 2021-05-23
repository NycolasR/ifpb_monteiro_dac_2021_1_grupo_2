package com.bookstore.com.bookstore.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * a classe Interface Strategy. 
 *
 */
@Entity
@Table(name = "TB_FORMA_PAGAMENTO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class FormaPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "NOME_TIPO")
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
	public abstract void realizarPagamento(BigDecimal valor);
	
	@Override
	public String toString() {
		return "Forma de pagamento: " + nomeTipo;
	}
	
	public boolean equals(FormaPagamento formaPagamento) {
		return formaPagamento.getID().equals(ID);
	}

}

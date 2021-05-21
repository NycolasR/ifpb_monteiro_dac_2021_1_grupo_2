package model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Estoque {

	private List<Livro> itensAdicionados;
	private List<Livro> itensExcluidos;

	
	public List<Livro> getItensAdicionados() {
		return itensAdicionados;
	}

	public void setItensAdicionados(List<Livro> itensAdicionados) {
		this.itensAdicionados = itensAdicionados;
	}

	public List<Livro> getItensExcluidos() {
		return itensExcluidos;
	}

	public void setItensExcluidos(List<Livro> itensExcluidos) {
		this.itensExcluidos = itensExcluidos;
	}
	
}

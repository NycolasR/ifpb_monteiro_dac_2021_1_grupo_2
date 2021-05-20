package model;

import java.util.ArrayList;

public class Estoque {

	private ArrayList<Livro> itensAdicionados;
	private ArrayList<Livro> itensExcluidos;

	
	public ArrayList<Livro> getItensAdicionados() {
		return itensAdicionados;
	}

	public void setItensAdicionados(ArrayList<Livro> itensAdicionados) {
		this.itensAdicionados = itensAdicionados;
	}

	public ArrayList<Livro> getItensExcluidos() {
		return itensExcluidos;
	}

	public void setItensExcluidos(ArrayList<Livro> itensExcluidos) {
		this.itensExcluidos = itensExcluidos;
	}
	
}

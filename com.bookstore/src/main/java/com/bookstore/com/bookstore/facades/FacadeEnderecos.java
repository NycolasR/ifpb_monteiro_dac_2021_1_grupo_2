package com.bookstore.com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Endereco;
import com.bookstore.com.bookstore.service.EnderecoService;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe Facade responsável por métodos relacionados a Endereco. Esse padrão de projeto
 * foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadeEnderecos {

	@Autowired
	private EnderecoService enderecoService;
	
	/**
	 * Esse metodo é ultilizado para facilitar a criação de um objeto(Endereco).
	 * @param rua Nome da rua 
	 * @param numero Numero da residencia
	 * @param bairro Bairro da residencia
	 * @param uf Sigla do Estado 
	 * @param cidade Nome da cidade
	 * @param complemento Possiveis complementos
	 * @param cep CEP da cidade
	 * @return Retorna um objeto(Endereco)
	 */
	public Endereco criarEndereco(
			String rua, 
			Integer numero, 
			String bairro, 
			String uf,
			String cidade,
			String complemento,
			Integer cep) {
		
		Endereco novoEndereco = new Endereco();
		novoEndereco.setRua(rua);
		novoEndereco.setNumero(numero);
		novoEndereco.setBairro(bairro);
		novoEndereco.setUF(uf);
		novoEndereco.setCidade(cidade);
		novoEndereco.setComplemento(complemento);
		novoEndereco.setCEP(cep);
		return novoEndereco;
	}
	
}

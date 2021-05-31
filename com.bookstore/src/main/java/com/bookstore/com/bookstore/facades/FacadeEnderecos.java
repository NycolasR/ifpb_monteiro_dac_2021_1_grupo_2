package com.bookstore.com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Endereco;
import com.bookstore.com.bookstore.service.EnderecoService;

@Component
public class FacadeEnderecos {

	@Autowired
	private EnderecoService enderecoService;
	
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

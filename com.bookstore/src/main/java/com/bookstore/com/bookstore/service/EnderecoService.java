package com.bookstore.com.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Endereco;
import com.bookstore.com.bookstore.model.ItemPedido;
import com.bookstore.com.bookstore.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	public EnderecoRepository enderecoRepository;
	
	public void salvar(Endereco endereco) {
		enderecoRepository.save(endereco);
		
	}
	
	public Optional<Endereco> recuperarPeloId(Long id){
		return enderecoRepository.findById(id);
	}
	
	public void atualizarEndereco(Endereco endereco) throws Exception {
		if(recuperarPeloId(endereco.getId()).isPresent()) {
			salvar(endereco);
		}else{
			throw new Exception("Este endereço ainda não foi cadastrado");
		}
	}
	
	public void deletarEndereco(Long id) {
		enderecoRepository.deleteById(id);
	}
	
	public void delatarTudo() {
		enderecoRepository.deleteAll();
	}
	
	
	
}

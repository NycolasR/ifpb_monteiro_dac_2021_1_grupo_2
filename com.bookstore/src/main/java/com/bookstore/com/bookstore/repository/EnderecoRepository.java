package com.bookstore.com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.com.bookstore.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public List<com.bookstore.com.bookstore.model.Endereco> findByNome(String nome);
	
}

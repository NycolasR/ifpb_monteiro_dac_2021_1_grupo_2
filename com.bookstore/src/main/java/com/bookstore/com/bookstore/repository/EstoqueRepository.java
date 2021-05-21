package com.bookstore.com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.com.bookstore.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	public List<com.bookstore.com.bookstore.model.Estoque> findByNome(String nome);
	
}

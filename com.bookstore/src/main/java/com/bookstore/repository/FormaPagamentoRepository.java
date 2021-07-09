package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.com.bookstore.model.FormaPagamento;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

	//Essa query realiza uma busca por uma forma de pagamento que seja do tipo passado como parâmetro
	@Query(value = "select * from tb_forma_pagamento where tipo = :tipo", nativeQuery = true)
	FormaPagamento findByTipo(@Param ("tipo") String tipo);
}

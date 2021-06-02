package com.bookstore.com.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.com.bookstore.model.Livro;

/**
 * 
 * @author NPG (Grupo 2)
 * Interface para acesso ao banco de dados voltado para a entidade Livro.
 * Implementações fornecidas pelo Spring, logo não é necessário a uma classe
 * implementar esta interface.
 * 
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	@Query("SELECT l FROM Livro l WHERE l.quantidadeEmEstoque > 0 ORDER BY preco")
	public Page<Livro> livrosEmEstoque(Pageable pageable);
	
}

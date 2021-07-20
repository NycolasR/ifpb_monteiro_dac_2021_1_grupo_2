package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.model.Categoria;

/**
 * 
 * @author NPG
 * Interface para acesso ao banco de dados voltado para a entidade Categoria.
 * Implementações fornecidas pelo Spring, logo não é necessário a uma classe
 * implementar esta interface.
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

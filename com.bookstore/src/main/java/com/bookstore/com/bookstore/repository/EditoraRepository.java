package com.bookstore.com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.com.bookstore.model.Editora;

/**
 * 
 * @author NPG (Grupo 2)
 * Interface para acesso ao banco de dados voltado para a entidade Editora.
 * Implementações fornecidas pelo Spring, logo não é necessário a uma classe
 * implementar esta interface.
 * 
 */
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

}

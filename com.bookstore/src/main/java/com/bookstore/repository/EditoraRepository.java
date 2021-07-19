package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Editora;

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
	
	//Essa query realiza a remoção de uma instância na tabela Editora caso não exista nenhum livro utilizando dessa Editora
	@Transactional
	@Modifying
	@Query(value = "delete from tb_editora where id = :id and (select count(*) from tb_livro where editora_fk = :id) = 0", nativeQuery = true)
	public void deletarEditora(@Param ("id") Long id);
}

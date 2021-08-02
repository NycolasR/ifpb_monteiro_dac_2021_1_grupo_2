package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	//Essa query realiza a remoção de uma instância na tabela Categoria caso não exista nenhum livro utilizando dessa Categoria
	@Transactional
	@Modifying
	@Query(value = "delete from tb_categoria where id = :id and (select count(*) from tb_livro_categoria where fk_categoria = :id) = 0", nativeQuery = true)
	public void deletarCategoria(@Param ("id") Long id);
}

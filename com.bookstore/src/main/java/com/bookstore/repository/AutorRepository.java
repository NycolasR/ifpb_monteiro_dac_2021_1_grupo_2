package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Autor;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

	public List<Autor> findByNome(String nome);
	
	//Essa query realiza a remoção de uma instância na tabela autor caso não exista nenhum livro utilizando desse Autor
	@Transactional
	@Modifying
	@Query(value = "delete from tb_autor where id = :id and (select count(*) from tb_livro_autor where fk_autor = :id) = 0", nativeQuery = true)
	public void deletarAutor(@Param ("id") Long id);
}

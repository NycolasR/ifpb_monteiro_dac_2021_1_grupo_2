package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Endereco;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	//Essa query realiza a remoção de uma instância na tabela endereco caso não exista nenhum pedido utilizando desse endereco
	@Transactional
	@Modifying
	@Query(value = "delete from tb_endereco where id = :id and (select count(*) from tb_pedido where endereco_fk = :id) = 0", nativeQuery = true)
	public void deletarEndereco(@Param ("id") Long id);
}

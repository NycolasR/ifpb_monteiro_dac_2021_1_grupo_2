package com.bookstore.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Pedido;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query(value = "select * from tb_pedido where usuario_id = :id and status_pedido = 'Não Finalizado'", nativeQuery = true)
	public Optional<Pedido> recuperarPedidoNaoFinalizadoDoUsuario(@Param("id") Long id);
	
	@Query(value = "select quantidade_itens from tb_pedido where usuario_id = :id and status_pedido = 'Não Finalizado'", nativeQuery = true)
	public Integer recuperarQuantidadeIntenPedidosNaoFinalizadosDoUsuario(@Param("id") Long id);
	
}

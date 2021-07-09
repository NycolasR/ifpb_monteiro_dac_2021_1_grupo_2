package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.com.bookstore.model.ItemPedido;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

}

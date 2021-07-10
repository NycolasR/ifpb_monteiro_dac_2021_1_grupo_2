package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.model.Usuario;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Metodo que busca todos os usuarios cadastrados com o nome passado por parametro.
	 * @param nome Nome que deseja-se pesquisar.
	 * @return Uma lista com os usuarios encontrados.
	 */
	public List<Usuario> findByNome(String nome);
	
	/**
	 * Metodo que busca todos os usuario cadastrados pelo email passado por parametro.
	 * @param email Email que deseja-se pesquisar.
	 * @return Uma lista com os usuarios encontrados.
	 */
	public List<Usuario> findByEmailIs(String email);
	
}

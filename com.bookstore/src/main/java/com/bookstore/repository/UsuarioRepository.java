package com.bookstore.repository;

import java.util.List;
import java.util.Optional;

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
	 * @param nome Nome do usuário que deseja-se pesquisar.
	 * @return Uma lista com os usuarios encontrados.
	 */
	public List<Usuario> findByNome(String nome);
	
	/**
	 * Metodo que busca todos os usuario cadastrados pelo email passado por parametro.
	 * @param email Email do usuário que deseja-se pesquisar.
	 * @return Uma lista com os usuarios encontrados.
	 */
	public List<Usuario> findByEmailIs(String email);
	
	/**
	 * Método usado para recuperar um optionar de usuário com base no email informado
	 * @param email Email do usuário que deseja-se pesquisar.
	 * @return O optional de usuário
	 */
	public Optional<Usuario> findByEmail(String email);
}

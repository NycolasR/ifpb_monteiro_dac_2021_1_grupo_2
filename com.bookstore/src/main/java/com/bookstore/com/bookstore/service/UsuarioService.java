package com.bookstore.com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Usuario;
import com.bookstore.com.bookstore.repository.UsuarioRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos do CRUD de Usuario.
 *
 *
 */
@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	
	/**
	 * Esse método salva um objeto(Usuario) no banco de dados.
	 * @param usuario Usuario que deseja-se salvar
	 * @throws Exception Caso já exista um usuario cadastrado com endereço de email do usuario passado por parametro. 
	 */
	public void salvar(Usuario usuario) throws Exception {
		if(!verificarExistencia(usuario.getEmail())) {
			usuarioRepository.save(usuario);			
		}else {
			String mensagem = "Ja existe um usuario cadastrado com o email " + usuario.getEmail();
			throw new Exception(mensagem);			
		}
	}
	
	/**
	 * Essa metodo atualiza as informações de um Usuario já cadastrado no banco de dados.
	 * @param usuario Usuario com as atualizações.
	 * @throws Exception Se o usuario não estiver cadastrado ou se o email tenha sido alterado. 
	 */
	public void atualizar(Usuario usuario) throws Exception {
		Usuario userTemp = usuarioPorEmail(usuario.getEmail());
		if(userTemp.equals(usuario)) {
			usuarioRepository.save(usuario);
		}else {
			throw new Exception("O Email nao pode ser altereado");
		}
	}
	
	/**
	 * Esse metodo deleta um objeto(Usuario) do banco de dados.
	 * @param email email do Usuario que deseja-se excluir.
	 * @throws Exception Caso nenhum usuario seja encontrado com o email passado por parametro.
	 */
	public void excluir(String email) throws Exception {
		usuarioRepository.delete(usuarioPorEmail(email));		
	}
	
	/**
	 * Esse metodo deleta todas as tuplas de Usuario no banco de dados.
	 */
	public void excluirTudo() {
		usuarioRepository.deleteAll();
	}
	
	/**
	 * Esse metodo busca um usuario no banco de dados pelo email.
	 * @param email Email do usuario que deseja-se encontrar
	 * @return Objeto(Usuario) encontrado
	 * @throws Exception Caso nenhum usuario seja encontrado com o email passado por parametro
	 */
	public Usuario usuarioPorEmail(String email)throws Exception{
		if(usuarioRepository.findByEmailIs(email).size() >= 1) 
			return (Usuario) usuarioRepository.findByEmailIs(email).get(0);
		
		throw new Exception("NÃ£o ha nenhum usuario com o email: " + email + " cadastrado.");
		
	}
	
	/**
	 * Esse metodo busca todos os usuaios com o nome iqual ao passado por parametro no banco  de dados.
	 * @param nome nome dos usuarios que deseja-se encontrar.
	 * @return Uma lista de usuarios encontrados
	 * @throws Exception caso nenhum usuario seja encontrado.
	 */
	public List<Usuario> usuarioPorNome(String nome)throws Exception{
		List<Usuario> usersTemp = usuarioRepository.findByNome(nome);
		if(usersTemp.size()>=1)
			return usersTemp;
		
		throw new Exception("Nenhum usuario com o nome " + nome + " encontrado");
	}
	
	/**
	 * Esse metodo recupera o total de usuarios cadastrado no banco de dados.
	 * @return Long com a quantidade.
	 */
	public Long quantidadeDeUsuariosCadastrados() {
		return usuarioRepository.count();
	}
	
	/**
	 * Esse metodo verifica se existe algum usuario cadastrado com o endereço de email passado por parametro.
	 * @param email Email o qual deseja-se fazer a verificação.
	 * @return retorna um valor booleano, true caso exista um usuario cadastrado com o email passado por parametro e false caso não.
	 */
	public boolean verificarExistencia(String email) {
		try {
			usuarioPorEmail(email);
		}catch (Exception e){
			return false;
		}
		
		return true;
	}
	
}

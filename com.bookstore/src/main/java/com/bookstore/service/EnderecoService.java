package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Endereco;
import com.bookstore.repository.EnderecoRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é responsavel pelos métodos do CRUD de Endereco.
 *
 */

@Service
public class EnderecoService {

	@Autowired
	public EnderecoRepository enderecoRepository;
	
	/**
	 * Esse metodo salva um objeto(Endereco) no banco de dados.
	 * @param endereco Endereco que deseja-se salvar.
	 */
	public void salvar(Endereco endereco) {
		enderecoRepository.save(endereco);
		
	}
	
	/**
	 * Esse metodo recupera um objeto(Endereco) do banco de dados pelo ID.
	 * @param id ID do objeto que se deseja recuperar
	 * @return retorna um Optional contendo um endereco.
	 */
	public Optional<Endereco> recuperarPeloId(Long id){
		return enderecoRepository.findById(id);
	}
	
	/**
	 * Método usado para retornar uma lista com todos os endereços cadastrados
	 * no banco de dados.
	 * @return A lista dos endereços cadastrados
	 */
	public List<Endereco> listarEnderecos() {
		return enderecoRepository.findAll();
	}
	
	/**
	 * Esse metodo atualiza as informações de um Endereco já cadastrado no banco de dados.
	 * @param endereco Endereco com as atualizações.
	 * @throws Exception Caso o endereco passado por parametro não esteja cadastrado no banco de dados. 
	 */
	public void atualizarEndereco(Endereco endereco) throws Exception {
		if(recuperarPeloId(endereco.getId()).isPresent()) {
			salvar(endereco);
		}else{
			throw new Exception("Este endereço ainda não foi cadastrado");
		}
	}
	
	/**
	 * Esse metodo deleta um objeto(Endereco) do banco de dados
	 * @param id ID do Endereco que deja-se excluir.
	 */
	public void deletarEndereco(Long id) {
		enderecoRepository.deletarEndereco(id);
	}
	
	/**
	 * Esse metodo deleta todas as tuplas de Endereco no banco de dados.
	 */
	public void delatarTudo() {
		enderecoRepository.deleteAll();
	}
	
	/**
	 * Método usado para saber se existem registros de Endereco no banco de dados
	 * @return True se existir pelo menos um registro.
	 */
	public boolean existemRegistros() {
		return enderecoRepository.count() > 0;
	}
	
	public boolean isEnderecoPresente(Long id) {
		return recuperarPeloId(id).isPresent();
	}
	
}

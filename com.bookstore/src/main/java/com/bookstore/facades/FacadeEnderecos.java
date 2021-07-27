package com.bookstore.facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.model.Endereco;
import com.bookstore.service.EnderecoService;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe Facade responsável por métodos relacionados a Endereco. Esse padrão de projeto
 * foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadeEnderecos {

	@Autowired
	private EnderecoService enderecoService;
	
	/**
	 * Esse metodo é ultilizado para facilitar a criação de um objeto(Endereco).
	 * @param rua Nome da rua 
	 * @param numero Numero da residencia
	 * @param bairro Bairro da residencia
	 * @param uf Sigla do Estado 
	 * @param cidade Nome da cidade
	 * @param complemento Possiveis complementos
	 * @param cep CEP da cidade
	 * @return Retorna um objeto(Endereco)
	 */
	public Endereco criarEndereco(
			String rua, 
			Integer numero, 
			String bairro, 
			String uf,
			String cidade,
			String complemento,
			Long cep) {
		
		Endereco novoEndereco = new Endereco();
		novoEndereco.setRua(rua);
		novoEndereco.setNumero(numero);
		novoEndereco.setBairro(bairro);
		novoEndereco.setUF(uf);
		novoEndereco.setCidade(cidade);
		novoEndereco.setComplemento(complemento);
		novoEndereco.setCEP(cep);
		
		enderecoService.salvar(novoEndereco);
		return novoEndereco;
	}

	public Endereco criarEndereco(Endereco endereco) throws Exception {

		enderecoService.salvar(endereco);

		return recuperarEndereco(endereco.getId());

	}
	
	/**
	 * Método responsável por recuperar um endereço salvo no banco pelo id
	 * @param id do Endereco
	 * @return retorna o endereco encontrado no banco
	 * @throws Exception lança excessão caso o id não corresponda a nenhum id de algum endereco
	 */
	public Endereco recuperarEndereco(Long id) throws Exception{
		
		Optional<Endereco> endereco = enderecoService.recuperarPeloId(id);
		
		if (endereco.isPresent()) {
			return endereco.get();
		}
		
		throw new Exception("[ERRO] Endereço inexistente");
	}

	public Endereco recuperarEnderecoNulo() {
		return new Endereco();
	}

	public void atualizarEndereco(Endereco enderecoDto, Long id) throws Exception {

		Endereco enderecoUpdate = recuperarEndereco(id);

		enderecoUpdate.setRua(enderecoDto.getRua());
		enderecoUpdate.setNumero(enderecoDto.getNumero());
		enderecoUpdate.setBairro(enderecoDto.getBairro());
		enderecoUpdate.setUF(enderecoDto.getUF());
		enderecoUpdate.setCidade(enderecoDto.getCidade());
		enderecoUpdate.setComplemento(enderecoDto.getComplemento());
		enderecoUpdate.setCEP(enderecoDto.getCEP());
		
		enderecoService.atualizarEndereco(enderecoUpdate);
	}
	
	public void removerAutor(Long id) throws Exception{
		recuperarEndereco(id);
		enderecoService.deletarEndereco(id);
	}
}



















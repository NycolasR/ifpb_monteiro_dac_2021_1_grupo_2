package com.bookstore.facades;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.model.Editora;
import com.bookstore.service.EditoraService;

/**
 * 
 * @author NPG (Grupo 2)
 * Classe Façade usada para interfacear certas funcionalidades relativas a Editora.
 * Esse padrão de projeto foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 */
@Component
public class FacadeEditoras {

	@Autowired
	private EditoraService editoraService;
	
	/**
	 * Método usado para obter uma instância de Editora já o extraindo do Optional.
	 * @param id ID da Editora que se deseja buscar
	 * @return O registro da Editora requisitada
	 * @throws Exception Lança exceção caso o registro não seja encontrado na base de dados.
	 */
	public Editora recuperarEditora(Long id) throws Exception {
		
		Optional<Editora> optional = editoraService.recuperarPeloId(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("[ERRO] Editora não encontrada na base de dados.");
	}
	
	/**
	 * Método usado para obter todas as instâncias de Editora do banco
	 * @return retorna uma lista das Editoras cadastradas no banco
	 */
	public List<Editora> recuperarEditoras(){
		return editoraService.listarEditoras();
	}
	
	/**
	 * Método retorna uma entidade com os atributos nulos, ou seja, vazios, para serem adicionados
	 * nos campos do fromulário
	 * @return retorna uma Editora com os atributos vazios
	 */
	public Editora recuperarEditoraNula() {
	
		return new Editora("", "");
	}

	/**
	 * Método usado para criar uma nova instância de Editora
	 * @param nome Nome da Editora
	 * @param cidade Cidade da Editora
	 * @return O registro de Editora pronto
	 * @throws Exception 
	 */
	public Editora criarEditora(Editora editora) throws Exception {
//		Editora editora = new Editora();
//		editora.setNome(nome);
//		editora.setCidade(cidade);
		
		editoraService.salvarEditora(editora);
		
		return recuperarEditora(editora.getId());
	}
	
	/**
	 * Método usado para atualizar uma Editora
	 * @param editoraDto editora com novos parâmetros para atualizar
	 * @param id da editora
	 * @throws Exception lança escessão caso não encontre a editora com id passado
	 */
	public void atualizarEditora(Editora editoraDto, Long id) throws Exception {
		
		Editora editoraUpdate = recuperarEditora(id);
		
		editoraUpdate.setNome(editoraDto.getNome());
		editoraUpdate.setCidade(editoraDto.getCidade());
		editoraUpdate.setLivros(editoraDto.getLivros());
		
		editoraService.atualizarEditora(editoraUpdate);
	}
	
	/**
	 * Método utilizado para remover uma editora do banco, caso nenhum livro esteja utilizando-a
	 * @param id da editora 
	 */
	public void removerEditora(Long id) throws Exception{
		recuperarEditora(id);
		editoraService.deletarPeloId(id);
	}
}





package com.bookstore.facades;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.service.EditoraService;

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
	 * Método usado para selecionar e obter um registro de uma Editora. Este registro pode ser um novo.
	 * @return A Editora escolhida.
	 */
	public Editora selecionarEditora() {
		Scanner scanner = new Scanner(System.in);
		
		// Verifica se há editoras cadastradas
		if(editoraService.existemRegistros()) {
			
			List<Editora> editoras = editoraService.listarEditoras();
			
			// Lista as editoras: cada linha possui o ID seguido do nome da editora
			System.out.println("Editoras Cadastradas:\n");
			for (Editora editora : editoras) {
				System.out.println("ID: " + editora.getId() + " | Nome: " + editora.getNome());
			}
			
			// Pergunta se o usuário deseja criar uma nova além das existentes
			System.out.print("Deseja salvar uma nova editora? (Y/N) ");
			String resposta = scanner.nextLine();
			
			
			if(resposta.equalsIgnoreCase("Y")) {
				return lerDadosDeNovaEditora(); // Criando uma nova editora
				
			} else { // Recuperando uma já existente
				
				System.err.print("Informe o ID da editora selecionada: ");
				Long id = Long.parseLong(scanner.nextLine());
				
				while(true) {
					try {
						return recuperarEditora(id);
					} catch (Exception e) {
						System.err.print(e.getMessage() + " Informe o ID novamente: ");
						id = Long.parseLong(scanner.nextLine());
					}
				}
			}
		} else { // Não existindo nenhum registro, força-se o cliente a criar uma nova
			
			System.out.println("[ERRO] Não existem editoras cadastradas no sistema.");
			System.out.println("Cadastrar Nova Editora");
			return lerDadosDeNovaEditora();
		}
		
	}
	
	/**
	 * Método usado para ler os dados de uma nova editora
	 * @return O registro de Editora pronto
	 */
	private Editora lerDadosDeNovaEditora() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Informe o nome da editora: ");
		String nome = scanner.nextLine();
		
		System.out.print("Informe a cidade da editora: ");
		String cidade = scanner.nextLine();
		
		return criarEditora(nome, cidade);
	}

	/**
	 * Método usado para criar uma nova instância de Editora
	 * @param nome Nome da Editora
	 * @param cidade Cidade da Editora
	 * @return O registro de Editora pronto
	 */
	public Editora criarEditora(String nome, String cidade) {
		Editora editora = new Editora();
		editora.setNome(nome);
		editora.setCidade(cidade);
		
		editoraService.salvarEditora(editora);
		
		return editoraService.recuperarPeloId(editora.getId()).get();
	}
	
	/**
	 * Método usado para atualizar uma Editora
	 * @param editora O registro que se deseja atualizar
	 */
	public void atualizarEditora(Editora editora) {
		editoraService.atualizarEditora(editora);
	}
}





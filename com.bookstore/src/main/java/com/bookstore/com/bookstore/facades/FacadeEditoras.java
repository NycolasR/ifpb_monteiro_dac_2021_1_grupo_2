package com.bookstore.com.bookstore.facades;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.service.EditoraService;

@Component
public class FacadeEditoras {

	@Autowired
	private EditoraService editoraService;
	
	public Editora recuperarEditora(Long id) throws Exception {
		Optional<Editora> optional = editoraService.recuperarPeloId(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("[ERRO] Editora não encontrada na base de dados.");
	}

	public Editora selecionarEditora() throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		if(editoraService.existemRegistros()) {
			
			List<Editora> editoras = editoraService.listarEditoras();
			
			System.out.println("Editoras Cadastradas:\n");
			for (Editora editora : editoras) {
				System.out.println("ID: " + editora.getId() + " | Nome: " + editora.getNome());
			}
			
			System.out.print("Deseja salvar uma nova editora? (Y/N) ");
			String resposta = scanner.nextLine();
			
			if(resposta.equalsIgnoreCase("Y")) {
				System.out.print("Informe o nome da editora: ");
				String nome = scanner.nextLine();
				
				System.out.print("Informe a cidade da editora: ");
				String cidade = scanner.nextLine();
				
				return criarEditora(nome, cidade);
			} else {
				
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
			
		}
		
		throw new Exception("[ERRO] Não existem editoras cadastradas no sistema.");
	}

	public Editora criarEditora(String nome, String cidade) {
		Editora editora = new Editora();
		editora.setNome(nome);
		editora.setCidade(cidade);
		
		editoraService.salvarEditora(editora);
		
		return editoraService.recuperarPeloId(editora.getId()).get();
	}
	
	public void atualizarEditora(Editora editora) {
		editoraService.atualizarEditora(editora);
	}
}





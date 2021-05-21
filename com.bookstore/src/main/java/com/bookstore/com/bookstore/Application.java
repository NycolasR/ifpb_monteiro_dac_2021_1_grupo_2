package com.bookstore.com.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.com.bookstore.model.Estoque;
import com.bookstore.com.bookstore.service.EstoqueService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private EstoqueService estoqueService;
	
	public Application(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Estoque estoque = new Estoque();
		estoque.setNome("Estoque_nome");
		
		estoqueService.salvar(estoque);
		
		System.out.println("Deu certo");
	}

}

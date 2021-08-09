package com.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.facades.FacadeUsuarios;

@SpringBootApplication
public class MainSegundaEntrega implements CommandLineRunner {
	
	private FacadeUsuarios facadeUsuarios;
	
	public MainSegundaEntrega(FacadeUsuarios facadeUsuarios) {
		this.facadeUsuarios = facadeUsuarios;
	}

	public static void main(String[] args) {
		SpringApplication.run(MainSegundaEntrega.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Por padrão, já se mantém o cadastro do administrador do sistema antes de iniciar a aplicação
		facadeUsuarios.cadastrarUsuario("admin", "admin@admin.com", "admin123", true);
		
		System.out.println("Server is running at port 8080");
	}
	
}
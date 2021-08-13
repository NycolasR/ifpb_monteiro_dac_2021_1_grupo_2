package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.model.Endereco;
import com.bookstore.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class RestControllerCrudEnderecos {
	
	@Autowired
	private EnderecoService enderecoService;
	
	// Get All
	@GetMapping("/listar")
	@ResponseStatus(code = HttpStatus.OK, reason = "Endereços encontrados na base de dados")
	public List<Endereco> listar() {
		System.out.println("Oi");
		if(!enderecoService.existemRegistros())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return enderecoService.listarEnderecos();
	}
	
	// Get
	@GetMapping("/consultar/{id}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Endereço encontrado na base de dados")
	public Endereco consultar(@PathVariable("id") Long id) {
		Optional<Endereco> endereco = enderecoService.recuperarPeloId(id);
		
		if(endereco.isPresent())
			return endereco.get();
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
	}
	
	// Create
	@PostMapping("/adicionar")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Endereço criado com sucesso")
	public void criarEndereco(
			@Valid @RequestBody Endereco endereco, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.toString());
		}
		
		enderecoService.salvar(endereco);
	}
	
	// Update
	@PutMapping("/atualizar/{id}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Endereço atualizado com sucesso")
	public void atualizarEndereco(
			@Valid @RequestBody Endereco endereco, 
			BindingResult result, 
			@PathVariable("id") Long id) {
		
		if(result.hasErrors()) {
			System.err.println("tetete");
			System.out.println(endereco);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.toString());
		}
		
		try {
			enderecoService.atualizarEndereco(endereco);
		} catch (Exception e) {
			System.err.println("tatata");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	// Delete
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Endereço removido com sucesso")
	public void deletarEndereco(@PathVariable("id") Long id) {
		
		if(enderecoService.isEnderecoPresente(id)) {
			enderecoService.deletarEndereco(id);
			return;
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}










package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeEnderecos;
import com.bookstore.model.Endereco;

@Controller
public class ControllerCrudEnderecos {
	
	@Autowired
	private FacadeEnderecos facadeEnderecos;
	
	@GetMapping("/endereco_form/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
		try {
			
			if(id > 0) {//os ids cadastrados no banco são maiores que 0
				 
				model.addAttribute("endereco", facadeEnderecos.recuperarEndereco(id));
			}else {
				model.addAttribute("endereco", facadeEnderecos.recuperarEnderecoNulo());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "enderecos/enderecos_form";
	}
	
	@PostMapping("/endereco_form_add")
	public String criarEndereco(@ModelAttribute Endereco endereco) {
		
		try {
			facadeEnderecos.criarEndereco(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/endereco_form/" + endereco.getId();
	}
	
	@PostMapping("/endereco_form_update/{id}")
	public String atualizarEndereco(@ModelAttribute Endereco endereco, @PathVariable("id") Long id) {
		
		
		try {
			facadeEnderecos.atualizarEndereco(endereco, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/endereco_form/" + endereco.getId();
	}
	
	@PostMapping("/endereco_form_remove/{id}")
	public String removerEndereco(@PathVariable("id") Long id) {
		
		try {
			facadeEnderecos.removerAutor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/endereco_form/" + id;
	}
}

package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeFormaPagamento;
import com.bookstore.model.TipoFormaPagamento;

@Controller
public class ControllerCrudFormaPagamento {

	@Autowired
	private FacadeFormaPagamento facadeFormaPagamento;
	
	@GetMapping("/formapagamento")
	public String recuperarFormasPagamento(Model model) {
		
		model.addAttribute("formaspagamento", facadeFormaPagamento.recuperarFormasDePagamento());
		
		return "formaspagamento/formaspagamento";
	}
	
	@PostMapping("/formapagamentoformadd/{tipo}")
	public String adicionarFormaPagamento(@PathVariable("tipo") TipoFormaPagamento tipo) {
		
		try {
			facadeFormaPagamento.criarFormaPagamento(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/formapagamento";
	}
	
	@PostMapping("/formapagamentoformremove/{id}")
	public String removerFormaPagamento(@PathVariable("id") Long id) {
		
		try {
			facadeFormaPagamento.removerFormaPagamento(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/formapagamento";
	}
}

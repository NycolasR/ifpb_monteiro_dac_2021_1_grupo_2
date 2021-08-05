package com.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadePedido;
import com.bookstore.model.Pedido;

@Controller
public class ControllerPedidos {
	
	@Autowired 
	private FacadePedido facadePedidos; 

	@PostMapping("/pedido_cancelar/{id}")
	public String cancelarPedido(
			@Valid @ModelAttribute Pedido pedido, 
			BindingResult result, 
			@PathVariable("id") Long id,
			Model model) {
		
		model.addAttribute("pedido", pedido);
		
		if(result.hasErrors()) {
			return "cadastro/cadastro";
		}
		
		try {
			
			facadePedidos.cancelarPedido(id, pedido.getMotivoCancelamento());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil";
	}
}

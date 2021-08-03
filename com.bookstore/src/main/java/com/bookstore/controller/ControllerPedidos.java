package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadePedido;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;

@Controller
public class ControllerPedidos {
	
	@Autowired 
	private FacadePedido facadePedidos; 

	@PostMapping("/pedido_cancelar/{id}")
	public String cancelarPedido(@PathVariable("id") Long id) {
		Pedido pedido;
		try {
			pedido = facadePedidos.recuperarPedido(id);
			pedido.setStatusPedido("CANCELADO");
			
			facadePedidos.atualizarPedido(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil";
	}
	
}

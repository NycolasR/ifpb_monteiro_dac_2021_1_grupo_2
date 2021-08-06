package com.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadePedido;
import com.bookstore.model.Pedido;

@Controller
public class ControllerPedidos {
	
	@Autowired
	private FacadePedido facadePedidos;
	
	@GetMapping("/pedido_cancelar/{id}")
	public String paginaCancelarPedido(@PathVariable("id") Long id, Model model) {
		
		try {
			Pedido pedido = facadePedidos.recuperarPedido(id);
			model.addAttribute("pedido", pedido);
			model.addAttribute("excecao", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "pedidos/pedidos_cancelar";
	}

	@PostMapping("/pedido_cancelar/{id}")
	public String cancelarPedido(
			@Valid @ModelAttribute Pedido pedido,
			BindingResult result,
			Model model) {
		
		try {
			
			Pedido pedidoReal = facadePedidos.recuperarPedido(pedido.getId());
			pedido.setDataCriacao(pedidoReal.getDataCriacao());
			pedido.setDataFechamento(pedidoReal.getDataFechamento());
			pedido.setQntdItens(pedidoReal.getQntdItens());
			pedido.setValorItensTotal(pedidoReal.getValorItensTotal());
			pedido.setStatusPedido(pedidoReal.getStatusPedido());
			pedido.setLocalDeEntrega(pedidoReal.getLocalDeEntrega());
			pedido.setFormaPagamento(pedidoReal.getFormaPagamento());

			if(result.hasErrors()) {
				model.addAttribute("excecao", "");
				return "pedidos/pedidos_cancelar";
			}
			
			facadePedidos.cancelarPedido(pedido.getId(), pedido.getMotivoCancelamento());
			
		} catch (Exception e) {
			model.addAttribute("excecao", e.getMessage());
			e.printStackTrace();
			return "pedidos/pedidos_cancelar";
		}
		
		return "redirect:/perfil";
	}
}

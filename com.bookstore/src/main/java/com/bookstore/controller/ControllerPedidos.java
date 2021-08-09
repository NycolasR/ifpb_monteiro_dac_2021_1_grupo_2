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

/**
 * 
 * @author NPG
 * Classe Controller responsável pelas rotas GET e POST responsáveis
 * pelas operações relativas aos pedidos.
 */
@Controller
public class ControllerPedidos {
	
	@Autowired
	private FacadePedido facadePedidos;
	
	/**
	 * Método que é acionado quando a rota GET /pedido_cancelar/{id} é acionada.
	 * @param id ID do pedido que se deseja cancelar
	 * @param model Atributo usado para manipulação e transferência
	 * dos dados entre a view e o controller.
	 * @return String com o path do arquivo html que deve ser aberto no navegador.
	 */
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

	/**
	 * Método que é acionado quando a rota POST /pedido_cancelar/{id} é acionada.
	 * @param pedido Pedido que se deseja ser cancelado
	 * @param result Objeto usado para verificar se há erros nos atributos do pedido
	 * @param model Atributo usado para manipulação e transferência
	 * dos dados entre a view e o controller.
	 * @return Redirect para a página de perfil
	 */
	@PostMapping("/pedido_cancelar/{id}")
	public String cancelarPedido(
			@Valid @ModelAttribute Pedido pedido,
			BindingResult result,
			Model model) {
		
		try {
			
			// O pedido real é recuperado com base no ID
			Pedido pedidoReal = facadePedidos.recuperarPedido(pedido.getId());
			
			// Os valores do pedido real são setados no model attribute
			// para que possa passar pelas checagens do bean validation
			pedido.setDataCriacao(pedidoReal.getDataCriacao());
			pedido.setDataFechamento(pedidoReal.getDataFechamento());
			pedido.setQntdItens(pedidoReal.getQntdItens());
			pedido.setValorItensTotal(pedidoReal.getValorItensTotal());
			pedido.setStatusPedido(pedidoReal.getStatusPedido());
			pedido.setLocalDeEntrega(pedidoReal.getLocalDeEntrega());
			pedido.setFormaPagamento(pedidoReal.getFormaPagamento());

			if(result.hasErrors()) {
				// Atributo responsável por armazenar a mensagem da exceção
				// lançada e, com isto, exibir ao usuário.
				model.addAttribute("excecao", "");
				
				return "pedidos/pedidos_cancelar";
			}
			
			facadePedidos.cancelarPedido(pedido.getId(), pedido.getMotivoCancelamento());
			
		} catch (Exception e) {
			// Setando a mensagem da exceção no atributo para que seja exibido na página 
			model.addAttribute("excecao", e.getMessage());
			e.printStackTrace();
			return "pedidos/pedidos_cancelar";
		}
		
		return "redirect:/perfil";
	}
}

package com.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeEnderecos;
import com.bookstore.facades.FacadeFormaPagamento;
import com.bookstore.facades.FacadePedido;
import com.bookstore.facades.FacadeUsuarios;
import com.bookstore.model.Endereco;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerCarrinho {

	@Autowired
	private FacadePedido facadePedido;
	
	@Autowired
	private FacadeFormaPagamento facadeFormaPagamento;
	
	@Autowired
	private FacadeEnderecos facadeEnderecos;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private String excecao = "";
	
	@GetMapping("/carrinho")
	public String recuperarCarrinho(Model model, @AuthenticationPrincipal Usuario usuario) {
		
		model.addAttribute("pedido", facadePedido.recuperarPedidoCarrinho(usuario.getId()));
		model.addAttribute("finalizar_pedido", facadePedido.recuperarPedidoNulo());
		model.addAttribute("formas_pagamento", facadeFormaPagamento.recuperarFormasDePagamento());
		model.addAttribute("enderecos", usuario.getEnderecos());
		model.addAttribute("endereco", facadeEnderecos.recuperarEnderecoNulo());
		model.addAttribute("excecao", excecao);
		
		return "carrinho/carrinho_compras";
	}
	
	@GetMapping("/adicionar_item/{id}")
	public String adicionarUm(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
		
		adicionarRemoverUm(1, usuario.getId(), id);
		
		return "redirect:/carrinho";
	}
	
	@GetMapping("/remover_item/{id}")
	public String removerUm(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
		
		adicionarRemoverUm(-1, usuario.getId(), id);
		
		return "redirect:/carrinho";
	}
	
	@PostMapping("/finalizarPedido")
	public String finalizarPedido(@ModelAttribute Pedido pedido, @AuthenticationPrincipal Usuario usuario) {
		
		if(pedido.getFormaPagamento() != null && pedido.getLocalDeEntrega() != null) {
			
			try {
				
				facadePedido.finalizarPedido(usuario.getId(), pedido.getLocalDeEntrega().getId(), pedido.getFormaPagamento().getID());
				excecao = "";
				
			} catch (Exception e) {
				e.printStackTrace();
				excecao = e.getMessage();
			}
			
		}else {
			excecao = "Você precisa escolher um endereço e uma forma de pagamento para finalizar o pedido!";
		}
		
		return "redirect:/carrinho";
	}
	
	@PostMapping("/adicionar_endereco")
	public String adicionarNovoEndereco(@Valid @ModelAttribute Endereco endereco, BindingResult result, @AuthenticationPrincipal Usuario usuario) {
		
		if(result.hasErrors()) {
			excecao = "Informações inválidas ao adicionar o endereço!";
			return "redirect:/carrinho";
		}
		
		try {
			usuario.addEndereco(endereco);
			facadeEnderecos.criarEndereco(endereco);
			usuarioService.atualizar(usuario);
			excecao = "";
		} catch (Exception e) {
			e.printStackTrace();
			excecao = "Informações inválidas ao adicionar o endereço!";
		}
		
		return "redirect:/carrinho";
		
	}
		
	private void adicionarRemoverUm(Integer quantidade, Long idUsuario, Long idLivro) {
		
		try {
			facadePedido.atualizarPedido(idUsuario, idLivro, quantidade);
			excecao = "";
		} catch (Exception e) {
			
			excecao = e.getMessage();
			
			e.printStackTrace();
		}
	}
	
	
}

package com.bookstore.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.model.Endereco;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerUsuario {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/perfil/{id}")
	public String recuperarPaginaPerfil(@PathVariable("id") Long id, Model model) {
	
		Usuario usuario;
		List<Endereco> enderecos;
		Set<Pedido> pedidos;
		
		try {
			usuario = usuarioService.recuperarPeloId(id);
			enderecos = usuario.getEnderecos();
			pedidos = usuario.getPedidos();
			
			model.addAttribute("usuario", usuario);
			model.addAttribute("enderecos", enderecos);
			model.addAttribute("pedidos", pedidos);
			
			for (Iterator i = pedidos.iterator(); i.hasNext();) {
				Pedido pedido = (Pedido) i.next();
				System.out.println(pedido.getValorItensTotal());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/profile";
	}
	
	
	/*
	 * oef
	 * 
	
	 * 
	 
	 * 
	 * 
	 */
}

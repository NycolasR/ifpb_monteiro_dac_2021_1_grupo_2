package com.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.facades.FacadeCategoria;
import com.bookstore.facades.FacadeLivros;
import com.bookstore.facades.FacadePedido;
import com.bookstore.model.ItemPedido;
import com.bookstore.model.Livro;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;

@Controller
public class ControllerTelaInicial {

	@Autowired
	private FacadeLivros facadeLivros;

	@Autowired
	private FacadeCategoria facadeCategoria;

	@Autowired
	private FacadePedido facadePedido;

	private Integer pagina = 1;
	private List<Integer> categorias = null;
	private List<Long> idsLivros = new ArrayList<Long>();
	private String stringDeBusca = "";
	private Integer quantidade;

	@GetMapping("/inicio")
	public String recuperarTelaInicial(Model model, @AuthenticationPrincipal Usuario usuario) {

		if(usuario != null) {

			if(idsLivros.size() > 0) {

				try {

					facadePedido.criarPedido(idsLivros, usuario.getId());

				} catch (Exception e) {}
			}

			quantidade = facadePedido.recuperarQuantidadeDeItensPedidos(usuario.getId());

		}else {

			quantidade = idsLivros.size();
		}

		model.addAttribute("usuario", usuario);
		model.addAttribute("quantidade", quantidade);

		try {
			
			if(facadeLivros.exitemRegistros()) {
				
				Page<Livro> livros = facadeLivros.paginarLivros("titulo", Direction.ASC, pagina, false, categorias,
						stringDeBusca);

				model.addAttribute("categorias", facadeCategoria.recuperarCategorias());
				model.addAttribute("livros", livros);
				model.addAttribute("numeracao", facadeLivros.criarPaginacao(livros.getTotalPages(), pagina));
				model.addAttribute("fim", livros.getTotalPages());
				model.addAttribute("condicao",1);
			
			}else {
				model.addAttribute("condicao",0);
				stringDeBusca = "";
			}

			

		} catch (Exception e) {
			stringDeBusca = "";
			model.addAttribute("condicao",0);
			e.printStackTrace();
			return "redirect:/inicio";
		}

		stringDeBusca = "";

		return "telasiniciais/tela-inicial";
	}

	@GetMapping("/ecolher_pagina_inicio/{id}")
	public String escolherPagina(@PathVariable Integer id) {

		pagina = id;

		return "redirect:/inicio";
	}

	@GetMapping("/buscar_categoria")
	public String buscarPorCategoria(@RequestParam("categorias") List<Integer> categorias) {

		this.categorias = null;

		if (categorias.size() > 1) {
			categorias.remove(0);
			this.categorias = categorias;
		}
		pagina = 1;

		return "redirect:/inicio";
	}

	@GetMapping("/buscar_nome")
	public String buscarPorNome(@RequestParam("nome_livro") String nomeLivro) {

		stringDeBusca = nomeLivro;

		return "redirect:/inicio";
	}

	@GetMapping("/adicionar_carrinho/{id}")
	public String adicionarNoCarrinho(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {

		if(!idsLivros.contains(id)) {
			idsLivros.add(id);
		}
		
		if (usuario != null) { // aqui eu verifico se tem um usuario comum logado no sistema

			try {

				facadePedido.criarPedido(idsLivros, usuario.getId());

			} catch (Exception e) {}

			idsLivros.removeAll(idsLivros);

		}

		return "redirect:/inicio";
	}
	
	@GetMapping("/fazer_logof")
	public String fazerLogof() {
		
		pagina = 1;
		idsLivros.removeAll(idsLivros);
		return "redirect:/logout";
	}
	
	@GetMapping("/voltar_inicio_carrinho")
	public String voltarInicio(@AuthenticationPrincipal Usuario usuario) {
		
		Pedido pedido = facadePedido.recuperarPedidoCarrinho(usuario.getId());
		
		idsLivros.removeAll(idsLivros);
		
		if(pedido.getItensPedidos().size() > 0) {
			
			for(ItemPedido item: pedido.getItensPedidos()) {
				
				idsLivros.add(item.getLivro().getId());
			}

		}
		
		return "redirect:/inicio";
	}

}

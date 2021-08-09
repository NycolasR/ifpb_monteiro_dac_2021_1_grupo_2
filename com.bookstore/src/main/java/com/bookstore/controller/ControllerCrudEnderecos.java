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
import com.bookstore.model.Endereco;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

/**
 * 
 * @author NPG
 * Classe Controller responsável pelas rotas GET e POST responsáveis
 * pelas operações de CRUD dos endereços do usuário logado no sistema
 */
@Controller
public class ControllerCrudEnderecos {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FacadeEnderecos facadeEnderecos;

	/**
	 * Método que é acionado quando a rota /voltar é acionada.
	 * @return String com o path do arquivo html que deve ser aberto no navegador.
	 */
	@GetMapping("/voltar")
	public String voltarParaTelaPerfil() {
		return "redirect:/perfil";
	}

	/**
	 * Método que é acionado quando a rota /endereco_form/{id} é acionada.
	 * @param id ID do endereço que se deseja manipular
	 * @param model Atributo usado para manipulação e transferência
	 * dos dados entre a view e o controller.
	 * @return String com o path do arquivo html do formulário de endereços
	 * que deve ser aberto no navegador.
	 */
	@GetMapping("/endereco_form/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {
		
		try {
			if(id > 0) { //os IDs cadastrados no banco são maiores que 0
				model.addAttribute("endereco", facadeEnderecos.recuperarEndereco(id));
			} else {
				// Obtem um endereço nulo para cadastrar um novo
				model.addAttribute("endereco", facadeEnderecos.recuperarEnderecoNulo());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "enderecos/enderecos_form";
	}
	
	/**
	 * Método que é acionado quando a rota /endereco_form_add é acionada.
	 * @param endereco Endereço que se deseja salvar no BD
	 * @param result Objeto usado para verificar se há erros nos atributos de endereço
	 * @param usuario O usuário logado no sistema
	 * @return String com redirect para o arquivo html que deve ser aberto no navegador.
	 */
	@PostMapping("/endereco_form_add")
	public String criarEndereco(
			@Valid @ModelAttribute Endereco endereco, 
			BindingResult result, 
			@AuthenticationPrincipal Usuario usuario) {
		
		if(result.hasErrors()) {
			return "enderecos/enderecos_form";
		}
		
		try {
			usuario.addEndereco(endereco);
			facadeEnderecos.criarEndereco(endereco);
			usuarioService.atualizar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil";
	}
	
	/**
	 * Método que é acionado quando a rota /endereco_form_update/{id} é acionada.
	 * @param endereco Endereço que se deseja atualizar no BD
	 * @param result Objeto usado para verificar se há erros nos atributos de endereço
	 * @param id ID do endereço que se deseja atualizar
	 * @return String com redirect para o arquivo html que deve ser aberto no navegador.
	 */
	@PostMapping("/endereco_form_update/{id}")
	public String atualizarEndereco(
			@Valid @ModelAttribute Endereco endereco, 
			BindingResult result, @PathVariable("id") Long id) {
		
		if(result.hasErrors()) {
			return "enderecos/enderecos_form";
		}
		
		try {
			facadeEnderecos.atualizarEndereco(endereco, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil";
	}
	
	/**
	 * Método que é acionado quando a rota /endereco_form_remove/{id} é acionada.
	 * @param id ID do endereço que se deseja remover
	 * @return String com redirect para o arquivo html que deve ser aberto no navegador.
	 */
	@PostMapping("/endereco_form_remove/{id}")
	public String removerEndereco(@PathVariable("id") Long id) {

		try {
			facadeEnderecos.removerEndereco(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/perfil";
	}
}

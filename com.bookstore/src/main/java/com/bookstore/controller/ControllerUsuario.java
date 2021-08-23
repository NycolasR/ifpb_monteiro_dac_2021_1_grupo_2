package com.bookstore.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeUsuarios;
import com.bookstore.model.Endereco;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

/**
 * 
 * @author NPG
 * Classe Controller responsável pelas rotas GET e POST responsáveis
 * pelas operações necessárias aos usuários e à tela de perfil
 */
@Controller
public class ControllerUsuario {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FacadeUsuarios facadeUsuarios;

	/**
	 * Método que é acionado quando a rota /perfil é acionada.
	 * @param usuario Usuário que está logado no sistema
	 * @param model Atributo usado para manipulação e transferência
	 * dos dados entre a view e o controller.
	 * @return String com o path do arquivo html do perfil que deve ser aberto no navegador.
	 */
	@GetMapping("/perfil")
	public String recuperarPaginaPerfil(@AuthenticationPrincipal Usuario usuario, Model model) {
	
		List<Endereco> enderecos;
		Set<Pedido> pedidos;
		
		try {
			usuario = usuarioService.recuperarPeloId(usuario.getId());
			enderecos = usuario.getEnderecos();
			pedidos = usuario.getPedidos();
			
			model.addAttribute("usuario", usuario);
			model.addAttribute("enderecos", enderecos);
			model.addAttribute("pedidos", pedidos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "user/profile";
	}
	
	/**
	 * Método que é acionado quando a rota /usuario_create é acionada.
	 * @param usuario Usuário que se deseja salvar no BD.
	 * @param result Objeto usado para verificar se há erros nos atributos do usuário.
	 * @param model Atributo usado para manipulação e transferência
	 * dos dados entre a view e o controller.
	 * @return Redirect para a tela de inicio.
	 */
	@PostMapping("/usuario_create")
	public String cadastrarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		
		// Verifica se há um membro com o email no sistema
		if(facadeUsuarios.estaPresenteNoBd(usuario.getEmail())) {
			// Se houver, não será permitido cadastrar outro cliente com o mesmo email
			// Será exibida essa mensagem de erro ao usuário
			model.addAttribute("excecao", "Já existe um cliente com este email cadastrado!\nPor gentileza, use outro email.");
			return "cadastro/cadastro";
		}
		
		if(result.hasErrors()) {
			model.addAttribute("excecao", "");
			return "cadastro/cadastro";
		}
		
		// É necessário encriptar a senha para que possa ser salva no BD
		String senhaEncriptada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		
		try {
			facadeUsuarios.cadastrarUsuario(usuario.getNome(), usuario.getEmail(), senhaEncriptada, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/inicio";
	}
	
	/**
	 * Método que é acionado quando a rota /usuario_update/{id} é acionada.
	 * @param usuario Usuário que se deseja atualizar.
	 * @param result Objeto usado para verificar se há erros nos atributos do usuário.
	 * @param id ID do usuário que se deseja atualizar
	 * @return Redirect para a tela de perfil.
	 */
	@PostMapping("/usuario_update/{id}")
	public String atualizarUsuario(
			@Valid @ModelAttribute Usuario usuario, 
			BindingResult result, 
			@PathVariable("id") Long id) {
		
		Usuario usuarioSalvo = null;
		try {
			usuarioSalvo = usuarioService.recuperarPeloId(id);
			usuario.setSenha(usuarioSalvo.getSenha());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(result.hasErrors()) {
			return "user/profile";
		}
		
		usuarioSalvo.setEmail(usuario.getEmail());
		usuarioSalvo.setNome(usuario.getNome());
		
		try {
			usuarioService.atualizar(usuarioSalvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/perfil";
	}
}

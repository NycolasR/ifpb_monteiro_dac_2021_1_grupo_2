package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

@Controller
public class ControllerUsuario {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/perfil/{id}")
	public String recuperarPaginaPerfil(@PathVariable("id") Long id, Model model) {
		
//		tr
//			Usuario usuario = usuarioService.recuperarPeloId(id);
//		
//			model.addAttributeusuario", uario);
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return "user/profile";
	}
	
}

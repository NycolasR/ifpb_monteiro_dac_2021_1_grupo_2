package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.facades.FacadeEditoras;
import com.bookstore.model.Editora;

@Controller
public class ControllerCrudEditoras {

	@Autowired
	private FacadeEditoras facadeEditoras;

	@GetMapping("/editora")
	public String recuperarEditoras(Model model) {

		model.addAttribute("editoras", facadeEditoras.recuperarEditoras());

		return "editoras/editoras";
	}

	@GetMapping("/editoraform/{id}")
	public String resgatarFormulario(@PathVariable("id") Long id, Model model) {

		try {

			if (id > 0) {// os ids cadastrados no banco são maiores que 0

				model.addAttribute("editora", facadeEditoras.recuperarEditora(id));

			} else {

				model.addAttribute("editora", facadeEditoras.recuperarEditoraNula());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "editoras/editorasform";
	}

	@PostMapping("/editoraformadd")
	public String criarEditora(@ModelAttribute Editora editora) {

		try {
			facadeEditoras.criarEditora(editora);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editora";
	}

	@PostMapping("/editoraformupdate/{id}")
	public String atualizarEditora(@ModelAttribute Editora editoraDto, @PathVariable("id") Long id) {

		try {
			facadeEditoras.atualizarEditora(editoraDto, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editora";
	}

	@PostMapping("/editoraformremove/{id}")
	public String removerEditora(@PathVariable("id") Long id) {

		try {
			facadeEditoras.removerEditora(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editora";
	}

}

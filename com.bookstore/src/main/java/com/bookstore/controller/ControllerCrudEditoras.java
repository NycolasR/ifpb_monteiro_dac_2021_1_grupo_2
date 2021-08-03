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

import com.bookstore.facades.FacadeEditoras;
import com.bookstore.model.Editora;

@Controller
public class ControllerCrudEditoras {

	@Autowired
	private FacadeEditoras facadeEditoras;
	
	private Long idFornecido;

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
				idFornecido = id;

			} else {

				model.addAttribute("editora", facadeEditoras.recuperarEditoraNula());
				idFornecido = id;
			}
			
			model.addAttribute("excecao","");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("editora", facadeEditoras.recuperarEditoraNula());
			model.addAttribute("excecao",e.getMessage());
		}
		
		model.addAttribute("isAdicionar", idFornecido);

		return "editoras/editorasform";
	}

	@PostMapping("/editoraformadd")
	public String criarEditora(@Valid @ModelAttribute Editora editora, BindingResult result, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("isAdicionar", 0);
			model.addAttribute("excecao","");
			return "editoras/editorasform";
		}
		
		try {
			facadeEditoras.criarEditora(editora);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editora";
	}

	@PostMapping("/editoraformupdate")
	public String atualizarEditora(@Valid @ModelAttribute Editora editoraDto, BindingResult result, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("excecao","");
			return "editoras/editorasform";
		}
		
		try {
			facadeEditoras.atualizarEditora(editoraDto, idFornecido);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/editoraform/" + idFornecido;
		}

		return "redirect:/editora";
	}

	@PostMapping("/editoraformremove")
	public String removerEditora() {

		try {
			facadeEditoras.removerEditora(idFornecido);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/editoraform/"+idFornecido;
		}

		return "redirect:/editora";
	}

}

package com.bookstore.facades;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.model.Categoria;
import com.bookstore.service.CategoriaService;

@Component
public class FacadeCategoria {

	@Autowired
	private CategoriaService categoriaService;
	
	public Categoria criarCategoria(Categoria categoria) throws Exception{
		
		categoriaService.salvarCategoria(categoria);
		return recuperarCategoria(categoria.getId());
	}
	
	public Categoria recuperarCategoria(Long id) throws Exception{
		
		Optional<Categoria> categoria = categoriaService.recuperarPeloId(id);
		
		if(categoria.isPresent()) {
	    	return categoria.get();
	    }
		
		throw new Exception("[ERRO] Categoria inexistente");
	}
	
	public List<Categoria> recuperarCategorias(){
		
		return categoriaService.listarCategorias();
	}
	
	public Categoria recuperarCategoriaNula() {
		
		return new Categoria("");
	}
	
	public void removerCategoria(Long id) throws Exception{
		recuperarCategoria(id);
		categoriaService.deletarPeloId(id);
	}
	
	
}

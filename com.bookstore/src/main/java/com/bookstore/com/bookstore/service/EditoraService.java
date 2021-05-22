package com.bookstore.com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.com.bookstore.model.Editora;
import com.bookstore.com.bookstore.repository.EditoraRepository;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;
	
	public void salvar(Editora editora) {
		editoraRepository.save(editora);
	}
}

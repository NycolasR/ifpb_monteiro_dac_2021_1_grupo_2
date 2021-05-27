package com.bookstore.com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.com.bookstore.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

	public List<Autor> findByNome(String nome);
}

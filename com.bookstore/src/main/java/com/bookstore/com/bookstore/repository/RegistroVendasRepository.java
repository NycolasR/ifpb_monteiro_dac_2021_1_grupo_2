package com.bookstore.com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.com.bookstore.model.RegistroVendas;

@Repository
public interface RegistroVendasRepository extends JpaRepository<RegistroVendas, Long> {
	
}

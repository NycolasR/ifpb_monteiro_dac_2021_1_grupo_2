package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerLogin {

	@GetMapping("/login")
	public String resgatarPaginaLogin() {
		return "login/login";
	}

}

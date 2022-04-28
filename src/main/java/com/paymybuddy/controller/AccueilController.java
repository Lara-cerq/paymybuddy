package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccueilController {

	@PostMapping(value = "/login/accueil")
	public String accueil() {
		return "accueil";
	}
}

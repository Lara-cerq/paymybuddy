package com.paymybuddy.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.service.UtilisateurService;

@Controller
public class LoginController {
	
	@Autowired
	private UtilisateurService utilisateurService;

//	@RolesAllowed("USER")
//	@RequestMapping("/*")
//	public String getUser() {
//		return "Welcome User";
//	}
//
//	@RolesAllowed({ "USER", "ADMIN" })
//	@RequestMapping("/admin")
//	public String getAdmin() {
//		return "Welcome Admin";
//	}

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() {	
		return "login";
	}
	
//	@RequestMapping("/emailNotExists.html")
//	public String emailNotExists(Model model,@ModelAttribute("utilisateur") Utilisateur utilisateur) {
//	Optional<Utilisateur> utilisateurExists=utilisateurService.findUtilisateurByEmail(utilisateur.getEmail());
//		
//		if(!utilisateurExists.isPresent()){
//		   return "Oops!  Cet email n'existe pas. Veuillez créer un compte";
//		}
//		model.addAttribute("emailNotExists", true);
//		return "login.html";
//	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}
}

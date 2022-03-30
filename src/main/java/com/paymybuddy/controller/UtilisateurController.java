package com.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.form.AmiForm;
import com.paymybuddy.form.UtilisateurForm;
import com.paymybuddy.model.Ami;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.service.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/accueil" }, method = RequestMethod.GET)
	public String pageAccueil() {
		return message;
	}

	@RequestMapping(value = { "/utilisateurList" }, method = RequestMethod.GET)
	public String utilisateurList(Model model) {
		List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
		model.addAttribute("utilisateurs", utilisateurs);

		return "utilisateurList";
	}

	@RequestMapping(value = { "/addUtilisateur" }, method = RequestMethod.GET)
	public String showAddUtilisateurPage(Model model) {

		UtilisateurForm utilisateurForm = new UtilisateurForm();
		model.addAttribute("utilisateurForm", utilisateurForm);

		return "addUtilisateur";
	}

	@PostMapping(value = { "/addUtilisateur" })
	@ResponseBody
	public String saveUtilisateur(Model model,@ModelAttribute("utilisateur") Utilisateur utilisateur) {

		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String password = utilisateur.getPassword();

//		if (firstName != null && firstName.length() > 0 //
//				&& lastName != null && lastName.length() > 0) {
		Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, password);
		utilisateurService.addUtilisateur(newUtilisateur);
//	 		persons.add(newPerson);

		return "Utilisateur crée avec succès!";
//		}

//		model.addAttribute("errorMessage", errorMessage);
//		return "addUtilisateur";
	}

	@RequestMapping(value = { "/addAmi" }, method = RequestMethod.GET)
	public String showAddAmiPage(Model model) {

		AmiForm amiForm = new AmiForm();
		model.addAttribute("amiForm", amiForm);

		return "addAmi";
	}

	@PostMapping(value = { "/addAmi" })
	@ResponseBody
	public String saveAmi(Model model,@ModelAttribute("ami") Ami ami) {

		String nom = ami.getNom();
		String prenom = ami.getPrenom();
		String email = ami.getEmail();
		String password = ami.getPassword();

//		if (firstName != null && firstName.length() > 0 //
//				&& lastName != null && lastName.length() > 0) {
		Ami newAmi = new Ami(nom, prenom, email, password);
		utilisateurService.addUtilisateur(newAmi);
//	 		persons.add(newPerson);

		return "Ami ajouté avec succès!";
//		}

//		model.addAttribute("errorMessage", errorMessage);
//		return "addUtilisateur";
	}

}

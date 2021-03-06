package com.paymybuddy.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.dto.AmiDto;
import com.paymybuddy.dto.UtilisateurDto;
import com.paymybuddy.model.ComptePayMyBuddy;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.service.CompteService;
import com.paymybuddy.service.UtilisateurService;


@Controller
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	CompteService compteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/utilisateurList" }, method = RequestMethod.GET)
	public String utilisateurList(Model model) {
		List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
		model.addAttribute("utilisateurs", utilisateurs);

		return "utilisateurList";
	}

	@RequestMapping(value = { "/addUtilisateur" }, method = RequestMethod.GET)
	public String showAddUtilisateurPage(Model model) {

		UtilisateurDto utilisateurDto = new UtilisateurDto();
		model.addAttribute("utilisateurDto", utilisateurDto);

		return "addUtilisateur";
	}

	@Transactional
	@PostMapping(value = { "/addUtilisateur" })
	public String saveUtilisateur(Model model, @ModelAttribute("email") String emailExists,
			@ModelAttribute("utilisateur") Utilisateur utilisateur) {

		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String password = passwordEncoder.encode(utilisateur.getPassword());

		Optional<Utilisateur> utilisateurExists = utilisateurService.findUtilisateurByEmail(emailExists);

		if (!utilisateurExists.isEmpty()) {
			return "Oops!  Cet email exists d??j??. Veuillez vous connecter";
		}

		Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, password, true);
		utilisateurService.addUtilisateur(newUtilisateur);
		//cr??ation d'un compte Pay My Buddys
		ComptePayMyBuddy compte= new ComptePayMyBuddy(newUtilisateur, "utilisateur", 0);
		compteService.addCompte(compte);

		return "redirect:transaction";
	}

	@RequestMapping(value = { "/addAmi" }, method = RequestMethod.GET)
	public String showAddAmiPage(Model model) {

		AmiDto amiDto = new AmiDto();
		model.addAttribute("amiDto", amiDto);

		return "addAmi";
	}

	@Transactional
	@PostMapping(value = { "/addAmi" })
	public String saveAmi(Model model, Principal utilisateurConnecte, Utilisateur utilisateurAmi) {
		String nom = utilisateurAmi.getNom();
		String prenom = utilisateurAmi.getPrenom();
		String email = utilisateurAmi.getEmail();

		Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, "", true);
		utilisateurService.addUtilisateur(newUtilisateur);

		String emailUtilisateurConnecte = utilisateurConnecte.getName();
		Optional<Utilisateur> utilisateurConect = utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
		Utilisateur utilisateur = utilisateurConect.get();
		newUtilisateur.setUtilisateurParent(utilisateur);
		List<Utilisateur> amisList = utilisateurService.ajouterAmi(newUtilisateur);
		Set<Utilisateur> amisListSet = new HashSet<>(amisList);
		//ajout de l'ami dans sa list d'amis
		utilisateur.setAmi(amisListSet);
		// cr??ation d'un compte Pay My Buddy
		ComptePayMyBuddy newConmpte = new ComptePayMyBuddy(newUtilisateur, "utilisateur", 0);
		compteService.addCompte(newConmpte);

		System.out.println("Ami ajout?? avec succ??s!");

		return "redirect:transaction";
	}

	@RequestMapping(value = { "/findEmail" }, method = RequestMethod.GET)
	public String showFindAmiPage(Model model) {

		AmiDto amiDto = new AmiDto();
		model.addAttribute("amiDto", amiDto);

		return "findEmail";
	}

	@PostMapping(value = { "/findEmail" })
	public String findUtilisateurByEmail(Model model, @ModelAttribute("email") String emailAmi,
			Principal utilisateurConnecte) {

		Optional<Utilisateur> optionalAmi = utilisateurService.findUtilisateurByEmail(emailAmi);

		if (optionalAmi.isEmpty()) {
			return "amiNonTrouve";
		} else {
			String emailUtilisateurConnecte = utilisateurConnecte.getName();
			Optional<Utilisateur> utilisateurConect = utilisateurService
					.findUtilisateurByEmail(emailUtilisateurConnecte);
			Utilisateur utilisateur = utilisateurConect.get();
			Utilisateur ami = optionalAmi.get();

			String nom = ami.getNom();
			String prenom = ami.getPrenom();
			String email = ami.getEmail();
			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, "", true);
			utilisateurService.addUtilisateur(newUtilisateur);

			newUtilisateur.setUtilisateurParent(utilisateur);
			List<Utilisateur> amisList = utilisateurService.ajouterAmi(ami);
			Set<Utilisateur> amisListSet = new HashSet<>(amisList);
			// ajout de l'ami directement dans ma list d'amis car il existe deja dans la base de donn??es
			utilisateur.setAmi(amisListSet);
			return "amiTrouve";
		}
	}

	@RequestMapping(value = { "/amiNonTrouve" }, method = RequestMethod.GET)
	public String showAmiNonTrouve(Model model) {

		AmiDto amiDto = new AmiDto();
		model.addAttribute("amiDto", amiDto);

		return "amiNonTrouve";
	}
}

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.dto.AmiDto;
import com.paymybuddy.dto.UtilisateurDto;
import com.paymybuddy.model.Ami;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.service.UtilisateurService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

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
	@ResponseBody
	public String saveUtilisateur(Model model,  @ModelAttribute("email") String emailExists, @ModelAttribute("utilisateur") Utilisateur utilisateur) {

		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String password = passwordEncoder.encode(utilisateur.getPassword());

		Optional<Utilisateur> utilisateurExists = utilisateurService.findUtilisateurByEmail(emailExists);

		if (!utilisateurExists.isEmpty()) {
			return "Oops!  Cet email exists déjà. Veuillez vous connecter";
		}

		Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, password, true);
		utilisateurService.addUtilisateur(newUtilisateur);

		return "Utilisateur crée avec succès!";
	}

	@RequestMapping(value = { "/addAmi" }, method = RequestMethod.GET)
	public String showAddAmiPage(Model model) {

		AmiDto amiDto = new AmiDto();
		model.addAttribute("amiDto", amiDto);

		return "addAmi";
	}

	@Transactional
	@PostMapping(value = { "/addAmi" })
	@ResponseBody
	public String saveAmi(Model model, Principal utilisateurConnecte, Utilisateur utilisateurAmi) {
//		public String saveAmi(Model model, @ModelAttribute("email") String emailAmi, Principal utilisateurConnecte, Utilisateur utilisateurAmi) {
//		Optional<Utilisateur> optionalAmi = utilisateurService.findUtilisateurByEmail(emailAmi);

//		if (optionalAmi.isEmpty()) {
//			System.out.println("Utilisateur inexistant");
			String nom = utilisateurAmi.getNom();
			String prenom = utilisateurAmi.getPrenom();
			String email = utilisateurAmi.getEmail();
			
			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, "",true);
			utilisateurService.addUtilisateur(newUtilisateur);
			
			String emailUtilisateurConnecte = utilisateurConnecte.getName();
			Optional<Utilisateur> utilisateurConect= utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
			Utilisateur utilisateur= utilisateurConect.get();
			newUtilisateur.setUtilisateurParent(utilisateur);
			List<Utilisateur> amisList= utilisateurService.ajouterAmi(newUtilisateur);
			Set<Utilisateur> amisListSet= new HashSet<>(amisList);
			utilisateur.setAmi(amisListSet);
			
			System.out.println("Ami ajouté avec succès!");
//		} else {
//			String emailUtilisateurConnecte = utilisateurConnecte.getName();
//			Optional<Utilisateur> utilisateurConect= utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
//			Utilisateur utilisateur= utilisateurConect.get();
//			Utilisateur ami=optionalAmi.get();
//
//			String nom = ami.getNom();
//			String prenom = ami.getPrenom();
//			String email = ami.getEmail();
//			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, "",true);
//			utilisateurService.addUtilisateur(newUtilisateur);
//			
//			newUtilisateur.setUtilisateurParent(utilisateur);
//			List<Utilisateur> amisList= utilisateurService.ajouterAmi(ami);
//			Set<Utilisateur> amisListSet= new HashSet<>(amisList);
//			utilisateur.setAmi(amisListSet);
//		}

//		if (firstName != null && firstName.length() > 0 //
//				&& lastName != null && lastName.length() > 0) {
//		Ami newAmi = new Ami(nom, prenom, email, password);
//		utilisateurService.addUtilisateur(newAmi);
//	 		persons.add(newPerson);

		return "Ami ajouté avec succès!";
//		}

//		model.addAttribute("errorMessage", errorMessage);
//		return "addUtilisateur";
	}
	
	@RequestMapping(value = { "/findEmail" }, method = RequestMethod.GET)
	public String showFindAmiPage(Model model) {

		AmiDto amiDto = new AmiDto();
		model.addAttribute("amiDto", amiDto);

		return "findEmail";
	}

	@PostMapping(value = { "/findEmail" })
	public String findUtilisateurByEmail(Model model, @ModelAttribute("email") String emailAmi, Principal utilisateurConnecte) {

		Optional<Utilisateur> optionalAmi = utilisateurService.findUtilisateurByEmail(emailAmi);

		if (optionalAmi.isEmpty()) {
			System.out.println("Ami non trouvé");
			return "amiNonTrouve";
		} else {
			System.out.println("Ami trouvé --> ajouté dans la liste");
			String emailUtilisateurConnecte = utilisateurConnecte.getName();
			Optional<Utilisateur> utilisateurConect= utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
			Utilisateur utilisateur= utilisateurConect.get();
			Utilisateur ami=optionalAmi.get();

			String nom = ami.getNom();
			String prenom = ami.getPrenom();
			String email = ami.getEmail();
			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, "",true);
			utilisateurService.addUtilisateur(newUtilisateur);
			
			newUtilisateur.setUtilisateurParent(utilisateur);
			List<Utilisateur> amisList= utilisateurService.ajouterAmi(ami);
			Set<Utilisateur> amisListSet= new HashSet<>(amisList);
			utilisateur.setAmi(amisListSet);
			return "redirect:transaction";
		}
	}
	
	@RequestMapping(value = { "/amiNonTrouve" }, method = RequestMethod.GET)
	public String showAmiNonTrouve(Model model) {

		AmiDto amiDto = new AmiDto();
		model.addAttribute("amiDto", amiDto);

		return "amiNonTrouve";
	}
}

package com.paymybuddy.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.dto.UtilisateurDto;
import com.paymybuddy.model.Compte;
import com.paymybuddy.model.ComptePayMyBuddy;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.service.CompteService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UtilisateurService;

@Controller
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	CompteService compteService;

	@RequestMapping(value = { "/transaction" }, method = RequestMethod.GET)
	public String transactionList(Model model, Principal utilisateurConnecte) {

		String emailUtilisateurConnecte = utilisateurConnecte.getName();
		Optional<Utilisateur> utilisateurOptional = utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
		Utilisateur utilisateur = utilisateurOptional.get();
		List<Transaction> transactionByUtilisateur = transactionService.getTransactionByUtilisateur(utilisateur);
		model.addAttribute("transactions", transactionByUtilisateur);

		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);
		
		List<Utilisateur> utilisateurList = utilisateurService.findAmisByUtilisateur(utilisateur);
		model.addAttribute("utilisateurs", utilisateurList);

		return "transaction";
	}

	@Transactional
	@PostMapping(value = { "/addTransaction" })
	public String addTransaction(Model model, Principal utilisateurConnecte,
			@ModelAttribute("transaction") Transaction transaction) {
		String destinataire = transaction.getDestinataire();
		String typetransaction= transaction.getTypeTransaction();
		Double montant = transaction.getMontant();
		Double cout = 0.5 * montant;

		String emailUtilisateurConnecte = utilisateurConnecte.getName();
		Optional<Utilisateur> utilisateurConect = utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
		Utilisateur utilisateur = utilisateurConect.get();
		
		if(typetransaction.equals("bancaire")) {
			ComptePayMyBuddy compte= new ComptePayMyBuddy(utilisateur, montant++);
			compteService.addCompte(compte);
		}
		
		Transaction newTransaction = new Transaction(destinataire, typetransaction, montant, cout, utilisateur);
		transactionService.addTransaction(newTransaction);
		return "redirect:transaction";
	}
//	
//	@GetMapping(value = { "/typeTransaction" })
//	public String showTypeTransaction(Model model, Principal utilisateurConnecte,
//			@ModelAttribute("typeTransaction") String typeTransaction) {
//		Transaction transaction = new Transaction();
//		model.addAttribute("transaction", transaction);
//		return "typeTransaction";
//	}
//	@PostMapping(value = { "/typeTransaction" })
//	public String choiceTypeTransaction(Model model, Principal utilisateurConnecte,
//			@ModelAttribute("typeTransaction") String typeTransaction, @ModelAttribute("transaction") Transaction transaction) {
//		if(transaction.getTypeTransaction().equals("bancaire")) {
//			return "transaction";
//		} else if(transaction.getTypeTransaction().equals("utilisateur")) {
//			return "typeTransaction";
//		}
//		return "typeTransaction";
//	}
}

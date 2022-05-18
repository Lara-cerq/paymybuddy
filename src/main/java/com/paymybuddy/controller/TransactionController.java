package com.paymybuddy.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.model.Compte;
import com.paymybuddy.model.ComptePayMyBuddy;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.TransactionBanque;
import com.paymybuddy.model.TransactionUtilisateur;
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
		List<Transaction> transactionsByUtilisateur = transactionService.getTransactionByUtilisateur(utilisateur);
		model.addAttribute("transactions", transactionsByUtilisateur);

		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);

		TransactionBanque transactionBanque = new TransactionBanque();
		model.addAttribute("transactionBanque", transactionBanque);

		TransactionUtilisateur transactionUtilisateur = new TransactionUtilisateur();
		model.addAttribute("transactionUtilisateur", transactionUtilisateur);

		// création d'un HashMap pour pouvoir afficher les destiataires car pour
		// certaines transactions(versement/transfert) il n'y a pas de destinataire
		String destinataire = "";
		List<String> destinataires = new ArrayList<>();
		Map<Transaction, String> transactionsMap = new HashMap<Transaction, String>();
		for (Transaction transactionBis : transactionsByUtilisateur) {
			Integer idTransactionUtilisateur = transactionBis.getIdTransaction();
			if (transactionBis.getTypeTransaction().equals("utilisateur")) {
				TransactionUtilisateur transactionUtilisateurById = transactionService
						.getTransactionsUtilisateurByIdTransaction(idTransactionUtilisateur);
				List<TransactionUtilisateur> transactionsUtilisateur = new ArrayList<>();
				transactionsUtilisateur.add(transactionUtilisateurById);
				destinataire = transactionUtilisateurById.getDestinataire();
				destinataires.add(destinataire);
			} else {
				destinataire = "";
				destinataires.add(destinataire);
			}
			transactionsMap.put(transactionBis, destinataire);
			model.addAttribute("transactionsMap", transactionsMap);
		}

		List<Utilisateur> utilisateurList = utilisateurService.findAmisByUtilisateur(utilisateur);
		model.addAttribute("utilisateurs", utilisateurList);

		List<Compte> comptes = compteService.getComptesByUtilisateur(utilisateur);
		model.addAttribute("comptes", comptes);
		// récuperer le solde
		ComptePayMyBuddy comptePayMyBuddy = compteService.getByUtilisateurAndTypeCompte(utilisateur, "utilisateur");
		double soldeUtilisateur = Math.round(comptePayMyBuddy.getSolde() * 100.0) / 100.0;
		model.addAttribute("compte", comptePayMyBuddy);
		model.addAttribute("solde", soldeUtilisateur);

		return "transaction";
	}

	@Transactional
	@PostMapping(value = { "/addTransaction" })
	public String addTransaction(Model model, Principal utilisateurConnecte,
			@ModelAttribute("transaction") Transaction transaction,
			@ModelAttribute("transactionUtilisateur") TransactionUtilisateur transactionUtilisateur, Errors errors,
			RedirectAttributes redirectAttributes) {
		Double montant = transaction.getMontant();
		Double cout = Math.round(0.005 * montant * 100.0) / 100.0;
		String emailUtilisateurConnecte = utilisateurConnecte.getName();
		Optional<Utilisateur> utilisateurConect = utilisateurService.findUtilisateurByEmail(emailUtilisateurConnecte);
		Utilisateur utilisateur = utilisateurConect.get();
		String typeTransaction = transaction.getTypeTransaction();
		// versement --> ajout du montant dans le solde
		if (typeTransaction.equals("versement")) {
			TransactionBanque newTransaction = new TransactionBanque(montant, cout, utilisateur, typeTransaction);
			transactionService.addTransaction(newTransaction);
			ComptePayMyBuddy compte = compteService.getByUtilisateurAndTypeCompte(utilisateur, "utilisateur");
			Double soldeExistant = compte.getSolde();
			compte.setSolde((soldeExistant + montant) - cout);
			// message de validation de la transaction
			redirectAttributes.addFlashAttribute("validMessage", "Transaction effectuée avec succès!");
			// utilisateur --> soustraction du montant au solde
		} else if (typeTransaction.equals("utilisateur")) {
			ComptePayMyBuddy compte = compteService.getByUtilisateurAndTypeCompte(utilisateur, "utilisateur");
			Double soldeExistant = compte.getSolde();
			Double soldeNouveau = (soldeExistant - montant) - cout;
			if (soldeNouveau >= 0) {
				String destinataire = transactionUtilisateur.getDestinataire();
				TransactionUtilisateur newTransactionUtilisateur = new TransactionUtilisateur(montant, cout,
						utilisateur, typeTransaction, destinataire);
				transactionService.addTransaction(newTransactionUtilisateur);
				compte.setSolde((soldeExistant - montant) - cout);
				// set solde compte ami
				List<Utilisateur> amiList = utilisateurService.getByPrenom(destinataire);
				Utilisateur ami = amiList.get(0);
				ComptePayMyBuddy compteAmi = compteService.getByUtilisateurAndTypeCompte(ami, "utilisateur");
				Double soldeAmi = compteAmi.getSolde();
				compteAmi.setSolde(soldeAmi + montant);
				// message de validation de la transaction
				redirectAttributes.addFlashAttribute("validMessage", "Transaction effectuée avec succès!");
				// affichage message d'erreur si solde insufisant
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"Solde insufisant! Veuillez réapprovisionner votre compte pour éffectuer cette opération.");
				return "redirect:transaction";
			}
			// transfert --> soustraction du montant au solde
		} else {
			ComptePayMyBuddy compte = compteService.getByUtilisateurAndTypeCompte(utilisateur, "utilisateur");
			Double soldeExistant = compte.getSolde();
			Double soldeNouveau = (soldeExistant - montant) - cout;
			if (soldeNouveau >= 0) {
				TransactionBanque newTransaction = new TransactionBanque(montant, cout, utilisateur, typeTransaction);
				transactionService.addTransaction(newTransaction);
				compte.setSolde((soldeExistant - montant) - cout);
				// message de validation de la transaction
				redirectAttributes.addFlashAttribute("validMessage", "Transaction effectuée avec succès!");
				// affichage message d'erreur si solde insufisant
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"Solde insufisant! Veuillez réapprovisionner votre compte pour éffectuer cette opération.");
				return "redirect:transaction";
			}
		}
		return "redirect:transaction";
	}
}

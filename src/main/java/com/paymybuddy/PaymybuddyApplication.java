package com.paymybuddy;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paymybuddy.model.Compte;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.service.CompteService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UtilisateurService;

@SpringBootApplication
public class PaymybuddyApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CompteService compteService;

	public static void main(String[] args) {
		SpringApplication.run(PaymybuddyApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Iterable<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
		utilisateurs.forEach(utilisateur -> System.out.println(utilisateur.getNom()));

		Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurById(1);
		Utilisateur utilisateurId = optUtilisateur.get();
		System.out.println(utilisateurId.getNom());
		
		Utilisateur newUtilisateur= new Utilisateur("cerqueira", "lara", "lara@st;com", "ljsh");
		newUtilisateur = utilisateurService.addUtilisateur(newUtilisateur);
		
		utilisateurs.forEach(utilisateur -> System.out.println(utilisateur.getNom()));
		
//		Compte newCompte = new Compte() {
//		});
//		newCompte.setTypeCompte("bancaire");
//		newCompte.setUtilisateur(newUtilisateur);
//		newCompte= compteService.addCompte(newCompte);
//		
//		Iterable<Compte> comptes = compteService.getAllComptes();
//		comptes.forEach(compte -> System.out.println(compte.getTypeCompte()));
		
		Utilisateur existingUtilisateur= utilisateurService.getUtilisateurById(1).get();
		
		existingUtilisateur.setNom("cerqueira");
		utilisateurService.addUtilisateur(existingUtilisateur);
		
		existingUtilisateur= utilisateurService.getUtilisateurById(1).get();
		
		System.out.println(existingUtilisateur.getNom());
		
//		utilisateurService.deleteById(1);
		
		
	}

}

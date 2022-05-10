package com.paymybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Compte;
import com.paymybuddy.model.ComptePayMyBuddy;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.repository.CompteRepository;

@Service
public class CompteService {

	@Autowired
	private CompteRepository compteRepository;

	public Iterable<Compte> getAllComptes() {
		return compteRepository.findAll();
	}

	public Optional<Compte> getCompteById(Integer id) {
		return compteRepository.findById(id);
	}

	public Compte addCompte(Compte compte) {
		return compteRepository.save(compte);
	}

	public List<Compte> getComptesByUtilisateur(Utilisateur utilisateur) {
		return compteRepository.getComptesByUtilisateur(utilisateur);
	}
	public ComptePayMyBuddy getSoldeByIdCompte(Integer idCompte) {
		return compteRepository.getSoldeByIdCompte(idCompte);
	}

	public ComptePayMyBuddy getByUtilisateurAndTypeCompte(Utilisateur utilisateur, String typeCompte) {
		return compteRepository.getByUtilisateurAndTypeCompte(utilisateur, typeCompte);
	}
}

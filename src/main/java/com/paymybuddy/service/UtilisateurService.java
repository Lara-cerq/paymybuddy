package com.paymybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.repository.UtilisateurRepository;

@Service
public class UtilisateurService {
	
	@Autowired
	 private UtilisateurRepository utilisateurRepository;
	
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurRepository.findAll();
		
	}
	
	public Utilisateur getUtilisateurById(Integer id) {
		return utilisateurRepository.getById(id);
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);		
	}
	
	public void deleteById(Integer id) {
		 utilisateurRepository.deleteById(id);
	}
}

package com.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Compte;
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
}

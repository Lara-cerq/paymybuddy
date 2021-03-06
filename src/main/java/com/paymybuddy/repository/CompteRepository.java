package com.paymybuddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Compte;
import com.paymybuddy.model.ComptePayMyBuddy;
import com.paymybuddy.model.Utilisateur;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Integer> {
	
	public List<Compte> getComptesByUtilisateur(Utilisateur utilisateur);
	
	public ComptePayMyBuddy getSoldeByIdCompte(Integer idCompte);
	
	public ComptePayMyBuddy getByUtilisateurAndTypeCompte(Utilisateur utilisateur, String typeCompte);

}

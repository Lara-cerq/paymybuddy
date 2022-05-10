package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CPMB")
public class ComptePayMyBuddy extends Compte {
//	
	@Column(name = "solde")
	private double solde;

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public ComptePayMyBuddy(Utilisateur utilisateur, double solde) {
		super(utilisateur);
		this.solde = solde;
	}

	public ComptePayMyBuddy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComptePayMyBuddy(Utilisateur utilisateur) {
		super(utilisateur);
		// TODO Auto-generated constructor stub
	}

	public ComptePayMyBuddy(double solde) {
		super();
		this.solde = solde;
	}

	public ComptePayMyBuddy(Utilisateur utilisateur, String typeCompte, double solde) {
		super(utilisateur, typeCompte);
		this.solde = solde;
	}

}

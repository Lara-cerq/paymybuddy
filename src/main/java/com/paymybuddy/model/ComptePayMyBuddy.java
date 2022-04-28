package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CPMB")
public class ComptePayMyBuddy extends Compte {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_compte")
//	private Integer idCompte;
//	
	@Column(name = "solde")
	private double solde;

//	public Integer getIdCompte() {
//		return idCompte;
//	}
//
//	public void setIdCompte(Integer idCompte) {
//		this.idCompte = idCompte;
//	}
//
	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
//
//	public ComptePayMyBuddy(Integer idCompte) {
//		super();
//		this.idCompte = idCompte;
//	}
//
//	public ComptePayMyBuddy() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public ComptePayMyBuddy(Utilisateur utilisateur, double solde) {
		super(utilisateur);
		this.solde = solde;
	}

//	public ComptePayMyBuddy(String typeCompte, Utilisateur utilisateur, double solde) {
//		super(typeCompte, utilisateur);
//		this.solde = solde;
//	}

}

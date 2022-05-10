package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TU")
public class TransactionUtilisateur extends Transaction {

	@Column(name = "destinataire")
	private String destinataire;

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public TransactionUtilisateur(String typeTransaction, double montant, double cout, String destinataire) {
		super(typeTransaction, montant, cout);
		this.destinataire = destinataire;
	}

	public TransactionUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionUtilisateur(double montant, double cout, Utilisateur utilisateur, String typeTransaction,
			String destinataire) {
		super(montant, cout, utilisateur, typeTransaction);
		this.destinataire = destinataire;
	}

	public TransactionUtilisateur(double montant, double cout, Utilisateur utilisateur) {
		super(montant, cout, utilisateur);
		// TODO Auto-generated constructor stub
	}

	public TransactionUtilisateur(String typeTransaction, double montant, double cout) {
		super(typeTransaction, montant, cout);
		// TODO Auto-generated constructor stub
	}

}

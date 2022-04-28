package com.paymybuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction")
	private Integer idTransaction;

	@Column(name = "destinataire")
	private String destinataire;

	@Column(name = "type_transaction")
	private String typeTransaction;

	@Column(name = "montant")
	private double montant;

	@Column(name = "cout")
	private double cout= 0.5*montant;
	// methode pour calcul du cout --> 0.5*montant

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Transaction(String typeTransaction, double montant, double cout) {
		super();
		this.typeTransaction = typeTransaction;
		this.montant = montant;
		this.cout = cout;
	}

	public Transaction(String destinataire, String typeTransaction, double montant, Utilisateur utilisateur) {
		super();
		this.destinataire = destinataire;
		this.typeTransaction = typeTransaction;
		this.montant = montant;
		this.utilisateur = utilisateur;
	}
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(String destinataire, String typeTransaction, double montant, double cout,
			Utilisateur utilisateur) {
		super();
		this.destinataire = destinataire;
		this.typeTransaction = typeTransaction;
		this.montant = montant;
		this.cout = cout;
		this.utilisateur = utilisateur;
	}

}

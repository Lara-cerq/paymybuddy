package com.paymybuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction")
	private Integer idTransaction;

	@Column(name = "montant")
	private double montant;

	@Column(name = "cout")
	private double cout;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;

	@Column(name = "type_transaction")
	private String typeTransaction;

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
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

	public Transaction(double montant, double cout, Utilisateur utilisateur, String typeTransaction) {
		super();
		this.montant = montant;
		this.cout = cout;
		this.utilisateur = utilisateur;
		this.typeTransaction = typeTransaction;
	}

	public Transaction(double montant, double cout, Utilisateur utilisateur) {
		super();
		this.montant = montant;
		this.cout = cout;
		this.utilisateur = utilisateur;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
}

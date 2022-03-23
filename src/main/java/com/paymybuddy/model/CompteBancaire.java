package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compte_bancaire")
public class CompteBancaire extends Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compte")
	private int idCompte;

	@Column(name = "numero_compte")
	private String numeroCompte;

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public CompteBancaire(int idCompte, String numeroCompte) {
		super();
		this.idCompte = idCompte;
		this.numeroCompte = numeroCompte;
	}

	public CompteBancaire() {
		super();
		// TODO Auto-generated constructor stub
	}

}

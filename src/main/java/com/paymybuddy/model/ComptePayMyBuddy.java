package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compte_pay_my_buddy")
public class ComptePayMyBuddy extends Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compte")
	private int idCompte;

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public ComptePayMyBuddy(int idCompte) {
		super();
		this.idCompte = idCompte;
	}

	public ComptePayMyBuddy() {
		super();
		// TODO Auto-generated constructor stub
	}

}

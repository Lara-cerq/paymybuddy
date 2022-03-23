package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_utilisateur")
public class TransactionUtilisateur extends Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction")
	private int idTransaction;

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public TransactionUtilisateur(int idTransaction) {
		super();
		this.idTransaction = idTransaction;
	}

	public TransactionUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

}

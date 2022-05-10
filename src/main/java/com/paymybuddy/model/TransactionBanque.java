package com.paymybuddy.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TB")
public class TransactionBanque extends Transaction {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_transaction")
//	private Integer idTransaction;
//
//	public Integer getIdTransaction() {
//		return idTransaction;
//	}
//
//	public void setIdTransaction(Integer idTransaction) {
//		this.idTransaction = idTransaction;
//	}

	public TransactionBanque(double montant, double cout, Utilisateur utilisateur) {
		super(montant, cout, utilisateur);
		// TODO Auto-generated constructor stub
	}

	public TransactionBanque(double montant, double cout, Utilisateur utilisateur, String typeTransaction) {
		super(montant, cout, utilisateur, typeTransaction);
		// TODO Auto-generated constructor stub
	}
//
//	public TransactionBanque(String typeTransaction, double montant, double cout) {
//		super(typeTransaction, montant, cout);
//		// TODO Auto-generated constructor stub
//	}

	public TransactionBanque() {
		super();
		// TODO Auto-generated constructor stub
	}
}

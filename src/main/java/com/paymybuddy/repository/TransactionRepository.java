package com.paymybuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.TransactionBanque;
import com.paymybuddy.model.TransactionUtilisateur;
import com.paymybuddy.model.Utilisateur;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	public List<Transaction> getTransactionByUtilisateur(Utilisateur utilisateur);

	public String getTypeTransactionByUtilisateur(Utilisateur utilisateur);

	public List<TransactionUtilisateur> getTransactionByTypeTransaction(String typeTransaction);

	public TransactionUtilisateur getTransactionUtilisateurByIdTransaction(Integer idTransaction);

	public TransactionBanque getTransactionBancaireByIdTransaction(Integer idTransaction);

}

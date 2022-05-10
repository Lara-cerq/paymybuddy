package com.paymybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.TransactionBanque;
import com.paymybuddy.model.TransactionUtilisateur;
import com.paymybuddy.model.Utilisateur;
import com.paymybuddy.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public Optional<Transaction> getTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}

	public Transaction addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getTransactionByUtilisateur(Utilisateur utilisateur) {
		return transactionRepository.getTransactionByUtilisateur(utilisateur);
	}

	public List<TransactionUtilisateur> getTransactionByTypeTransaction(String typeTransaction) {
		return transactionRepository.getTransactionByTypeTransaction(typeTransaction);
	}

	public TransactionUtilisateur getTransactionsUtilisateurByIdTransaction(Integer idTransaction) {
		return transactionRepository.getTransactionUtilisateurByIdTransaction(idTransaction);
	}

	public TransactionBanque getTransactionBancaireByIdTransaction(Integer idTransaction) {
		return transactionRepository.getTransactionBancaireByIdTransaction(idTransaction);
	}

	public String getTypeTransactionByUtilisateur(Utilisateur utilisateur) {
		return transactionRepository.getTypeTransactionByUtilisateur(utilisateur);
	}
}

package com.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Iterable<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public Optional<Transaction> getTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}
	
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}

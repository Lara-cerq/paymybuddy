package com.paymybuddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.Utilisateur;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	public List<Transaction> getTransactionByUtilisateur(Utilisateur utilisateur);

}

package com.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Compte;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Integer> {

}

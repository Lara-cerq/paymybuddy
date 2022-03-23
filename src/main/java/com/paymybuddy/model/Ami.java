package com.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ami")
public class Ami {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ami")
	private int idAmi;

	@Column(name = "id_utilisateur")
	private int idUtilisateur;

	@ManyToMany
	@JoinTable(name = "utilisateurs_amis")
	List<Utilisateur> utilisateurs = new ArrayList<>();

	public int getIdAmi() {
		return idAmi;
	}

	public void setIdAmi(int idAmi) {
		this.idAmi = idAmi;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Ami() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package com.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

//@Entity
//@Table(name = "ami")
//@IdClass(AmiPk.class)
//@DiscriminatorValue("A")
//@PrimaryKeyJoinColumn(name="id_Utilisateur")
public class Ami{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_utilisateur")
//	private Integer idUtilisateur;
//
//	@ManyToMany
//	@JoinTable(name = "utilisateurs_amis")
//	List<Utilisateur> utilisateurs = new ArrayList<>();
//
//	public Integer getIdUtilisateur() {
//		return idUtilisateur;
//	}
//
//	public void setIdUtilisateur(Integer idUtilisateur) {
//		this.idUtilisateur = idUtilisateur;
//	}
//
//	public List<Utilisateur> getUtilisateurs() {
//		return utilisateurs;
//	}
//
//	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
//		this.utilisateurs = utilisateurs;
//	}
//
//	public Ami() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Ami(String nom, String prenom, String email, String password) {
//		super(nom, prenom, email, password);
//	}
//
//	
}

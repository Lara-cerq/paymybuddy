package com.paymybuddy.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Embeddable
public class AmiPk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ami")
	private Integer idAmi;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private Integer idUtilisateur;

	public Integer getIdAmi() {
		return idAmi;
	}

	public void setIdAmi(Integer idAmi) {
		this.idAmi = idAmi;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public AmiPk(Integer idAmi, Integer idUtilisateur) {
		super();
		this.idAmi = idAmi;
		this.idUtilisateur = idUtilisateur;
	}

	public AmiPk() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAmi, idUtilisateur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmiPk other = (AmiPk) obj;
		return Objects.equals(idAmi, other.idAmi) && Objects.equals(idUtilisateur, other.idUtilisateur);
	}

}

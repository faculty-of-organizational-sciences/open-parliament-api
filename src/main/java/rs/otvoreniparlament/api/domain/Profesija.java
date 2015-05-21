package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "profesija")
public class Profesija {

	private Integer profesijaID;
	private String nazivProfesije;

	public Profesija() {
	}

	public Profesija(Integer profesijaID, String nazivProfesije) {
		this.profesijaID = profesijaID;
		this.nazivProfesije = nazivProfesije;
	}

	@Id
	@Column(name = "idprofesija")
	public Integer getProfesijaID() {
		return profesijaID;
	}

	public void setProfesijaID(Integer profesijaID) {
		this.profesijaID = profesijaID;
	}

	@Basic
	@Column(name = "naziv")
	public String getNazivProfesije() {
		return nazivProfesije;
	}

	public void setNazivProfesije(String nazivProfesije) {
		this.nazivProfesije = nazivProfesije;
	}

	@Override
	public String toString() {
		return "Profesija [ProfesijaID=" + profesijaID + ", nazivProfesije="
				+ nazivProfesije + "]";
	}
}

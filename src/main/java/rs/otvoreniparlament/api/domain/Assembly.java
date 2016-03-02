package rs.otvoreniparlament.api.domain;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saziv")
public class Assembly {

	@Id
	@Column(name = "idsaziv")
	private Integer assemblyID;

	@Column(name = "rec_date")
	private Timestamp rec_date;

	@Column(name = "active")
	private String active;

	@Column(name = "datumizbora")
	private Date electionDate;

	@Column(name = "naziv")
	private String name;

	@Column(name = "opis")
	private String about;

	public Assembly() {
	}

	public Assembly(Integer assemblyID, Timestamp rec_date, String active,
			Date electionDate, String name, String about) {
		this.assemblyID = assemblyID;
		this.rec_date = rec_date;
		this.active = active;
		this.electionDate = electionDate;
		this.name = name;
		this.about = about;
	}

	public Integer getAssemblyID() {
		return assemblyID;
	}

	public void setAssemblyID(Integer assemblyID) {
		this.assemblyID = assemblyID;
	}

	public Timestamp getRec_date() {
		return rec_date;
	}

	public void setRec_date(Timestamp rec_date) {
		this.rec_date = rec_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "Assembly [assemblyID=" + assemblyID + ", rec_date="
				+ rec_date + ", active=" + active + ", electionDate="
				+ electionDate + ", name=" + name + ", about=" + about + "]";
	}

}

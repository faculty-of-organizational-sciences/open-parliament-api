package rs.otvoreniparlament.api.domain;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saziv")
public class Gathering {

	@Id
	@Column(name = "idsaziv")
	private Integer gatheringID;

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

	public Gathering() {
	}

	public Gathering(Integer gatheringID, Timestamp rec_date, String active,
			Date electionDate, String name, String about) {
		this.gatheringID = gatheringID;
		this.rec_date = rec_date;
		this.active = active;
		this.electionDate = electionDate;
		this.name = name;
		this.about = about;
	}

	public Integer getGatheringID() {
		return gatheringID;
	}

	public void setGatheringID(Integer gatheringID) {
		this.gatheringID = gatheringID;
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
		return "Gathering [gatheringID=" + gatheringID + ", rec_date="
				+ rec_date + ", active=" + active + ", electionDate="
				+ electionDate + ", name=" + name + ", about=" + about + "]";
	}

}

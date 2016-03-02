package rs.otvoreniparlament.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import rs.otvoreniparlament.api.domain.meta.RestServiceName;

@Entity
@Table(name = "plenarnasednica")
@RestServiceName("sessions")
public class PlenarySession {

	@Id
	@Column(name = "idplenarnasednica")
	private Integer id;

	@Column(name = "tekstdnevnogreda")
	private String agenda;

	@Column(name = "teksttranskriptaplenarnesednice")
	private String transcriptText;

	@Column(name = "datumplenarnesednice")
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getTranscriptText() {
		return transcriptText;
	}

	public void setTranscriptText(String transcriptText) {
		this.transcriptText = transcriptText;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PlenarySession [id=" + id + ", agenda=" + agenda + ", transcriptText=" + transcriptText + "]";
	}

}

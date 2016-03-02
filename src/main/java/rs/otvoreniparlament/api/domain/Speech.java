package rs.otvoreniparlament.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.otvoreniparlament.api.domain.meta.RestServiceName;

@Entity
@Table(name = "obracanje")
@RestServiceName("speeches")
public class Speech {

	@Id
	@Column(name = "idobracanje")
	private Integer id;

	@Column(name = "tekst")
	private String text;

	@ManyToOne
	@JoinColumn(name = "idposlanika")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "idplenarnesednice")
	private PlenarySession plenarySession;

	@Column(name = "datumsednice")
	private Date sessionDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public PlenarySession getPlenarySession() {
		return plenarySession;
	}

	public void setPlenarySession(PlenarySession plenarySession) {
		this.plenarySession = plenarySession;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	@Override
	public String toString() {
		return "Speech [id=" + id + ", text=" + text + ", plenarnaSednica=" + plenarySession
				+ ", sessionDate=" + sessionDate + "]";
	}

}

package rs.otvoreniparlament.api.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "obracanje")
public class Speech {

	@Id
	@Column(name = "idobracanje")
	private Integer id;

	@Column(name = "tekst")
	private String text;

	@Column(name = "idposlanika")
	private Integer memberId;

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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
		return "Speech [id=" + id + ", text=" + text + ", memberId=" + memberId + ", plenarnaSednica=" + plenarySession
				+ ", sessionDate=" + sessionDate + "]";
	}

}

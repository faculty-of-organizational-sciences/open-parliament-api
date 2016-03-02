package rs.otvoreniparlament.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lista")
public class PartyList {

	@Id
	@Column(name = "idlista")
	private Integer id;

	@Column(name = "datumucestvovanjalistenaizborima")
	private Date partyListSelectionParticipationDate;

	@Column(name = "naziv")
	private String name;

	public PartyList() {
	}

	public PartyList(Integer partyListID,
			Date partyListSelectionParticipationDate, String name) {
		this.id = partyListID;
		this.partyListSelectionParticipationDate = partyListSelectionParticipationDate;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPartyListSelectionParticipationDate() {
		return partyListSelectionParticipationDate;
	}

	public void setPartyListSelectionParticipationDate(
			Date partyListSelectionParticipationDate) {
		this.partyListSelectionParticipationDate = partyListSelectionParticipationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PartyList [partyListID=" + id
				+ ", partyListSelectionParticipationDate="
				+ partyListSelectionParticipationDate + ", name=" + name + "]";
	}

}

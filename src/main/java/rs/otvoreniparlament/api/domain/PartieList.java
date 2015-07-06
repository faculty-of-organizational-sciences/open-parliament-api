package rs.otvoreniparlament.api.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lista")
public class PartieList {

	@Id
	@Column(name = "idlista")
	private Integer partieListID;

	@Column(name = "datumucestvovanjalistenaizborima")
	private Date partieListSelectionParticipationDate;

	@Column(name = "naziv")
	private String name;

	public PartieList() {
	}

	public PartieList(Integer partieListID,
			Date partieListSelectionParticipationDate, String name) {
		this.partieListID = partieListID;
		this.partieListSelectionParticipationDate = partieListSelectionParticipationDate;
		this.name = name;
	}

	public Integer getPartieListID() {
		return partieListID;
	}

	public void setPartieListID(Integer partieListID) {
		this.partieListID = partieListID;
	}

	public Date getPartieListSelectionParticipationDate() {
		return partieListSelectionParticipationDate;
	}

	public void setPartieListSelectionParticipationDate(
			Date partieListSelectionParticipationDate) {
		this.partieListSelectionParticipationDate = partieListSelectionParticipationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PartieList [partieListID=" + partieListID
				+ ", partieListSelectionParticipationDate="
				+ partieListSelectionParticipationDate + ", name=" + name + "]";
	}

}

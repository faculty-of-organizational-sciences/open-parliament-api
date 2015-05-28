package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "drzava")
public class Drzava {

	private Integer drzavaID;

	private String imeDrzave;

	private Integer grupePrijateljstvaID;

	public Drzava() {

	}

	public Drzava(Integer drzavaID, String imeDrzave, Integer grupePrijateljstvaID) {
		this.drzavaID = drzavaID;
		this.imeDrzave = imeDrzave;
		this.grupePrijateljstvaID = grupePrijateljstvaID;
	}

	@Id
	@Column(name = "iddrzava")
	public Integer getDrzavaID() {
		return drzavaID;
	}

	public void setDrzavaID(Integer drzavaID) {
		this.drzavaID = drzavaID;
	}

	@Basic
	@Column(name = "ime")
	public String getImeDrzave() {
		return imeDrzave;
	}

	public void setImeDrzave(String imeDrzave) {
		this.imeDrzave = imeDrzave;
	}

	@Basic
	@Column(name = "idgrupeprijateljstva")
	public Integer getGrupePrijateljstvaID() {
		return grupePrijateljstvaID;
	}

	public void setGrupePrijateljstvaID(Integer grupePrijateljstvaID) {
		this.grupePrijateljstvaID = grupePrijateljstvaID;
	}

	@Override
	public String toString() {
		return "Drzava [drzavaID=" + drzavaID + ", imeDrzave=" + imeDrzave + ", grupePrijateljstvaID="
				+ grupePrijateljstvaID + "]";
	}

}

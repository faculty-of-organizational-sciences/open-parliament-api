package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "mesto")
public class Mesto {

	private Integer mestoID;
	private String nazivMesta;
	private Integer udaljenostOdBG;
	private Integer okrugID;
	private Integer drzavaID;

	public Mesto() {
	}

	public Mesto(Integer mestoID, String nazivMesta, Integer udaljenostOdBG,
			Integer okrugID, Integer drzavaID) {
		this.mestoID = mestoID;
		this.nazivMesta = nazivMesta;
		this.udaljenostOdBG = udaljenostOdBG;
		this.okrugID = okrugID;
		this.drzavaID = drzavaID;
	}

	@Id
	@Column(name = "idmesto")
	public Integer getMestoID() {
		return mestoID;
	}

	public void setMestoID(Integer mestoID) {
		this.mestoID = mestoID;
	}

	@Basic
	@Column(name = "ime")
	public String getNazivMesta() {
		return nazivMesta;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

	@Basic
	@Column(name = "udaljenostodbeograda")
	public Integer getUdaljenostOdBG() {
		return udaljenostOdBG;
	}

	public void setUdaljenostOdBG(Integer udaljenostOdBG) {
		this.udaljenostOdBG = udaljenostOdBG;
	}

	@Basic
	@Column(name = "idokruga")
	public Integer getOkrugID() {
		return okrugID;
	}

	public void setOkrugID(Integer okrugID) {
		this.okrugID = okrugID;
	}

	@Basic
	@Column(name = "iddrzave")
	public Integer getDrzavaID() {
		return drzavaID;
	}

	public void setDrzavaID(Integer drzavaID) {
		this.drzavaID = drzavaID;
	}

	@Override
	public String toString() {
		return "Mesto [mestoID=" + mestoID + ", nazivMesta=" + nazivMesta
				+ ", udaljenostOdBG=" + udaljenostOdBG + ", okrugID=" + okrugID
				+ ", drzavaID=" + drzavaID + "]";
	}

}

package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "grupaprijateljstva")


public class GrupePrijateljstva {

	private Integer grupaPrijateljstvaID;
	private String imeGrupePrijateljstva;
	private String opisGrupePrijateljstva;
	
	public GrupePrijateljstva () {
		
	}
	
	public GrupePrijateljstva ( Integer grupaPrijateljstvaID, String imeGrupePrijateljstva, String opisGrupePrijateljstva) {
		this.grupaPrijateljstvaID = grupaPrijateljstvaID;
		this.imeGrupePrijateljstva = imeGrupePrijateljstva;
		this.opisGrupePrijateljstva = opisGrupePrijateljstva;
	}

	@Id
	@Column(name= "idgrupaprijateljstva")
	public Integer getGrupaPrijateljstvaID() {
		return grupaPrijateljstvaID;
	}

	public void setGrupaPrijateljstvaID(Integer grupaPrijateljstvaID) {
		this.grupaPrijateljstvaID = grupaPrijateljstvaID;
	}

	@Basic
	@Column(name = "ime")
	public String getImeGrupePrijateljstva() {
		return imeGrupePrijateljstva;
	}

	public void setImeGrupePrijateljstva(String imeGrupePrijateljstva) {
		this.imeGrupePrijateljstva = imeGrupePrijateljstva;
	}

	@Basic
	@Column(name= "opis")
	public String getOpisGrupePrijateljstva() {
		return opisGrupePrijateljstva;
	}

	public void setOpisGrupePrijateljstva(String opisGrupePrijateljstva) {
		this.opisGrupePrijateljstva = opisGrupePrijateljstva;
	}
	
	@Override
	public String toString() {
		return "GrupePrijateljstva [grupaPrijateljstvaID=" + grupaPrijateljstvaID + ", imeGrupe=" + imeGrupePrijateljstva
				+ ", opisGrupe=" + opisGrupePrijateljstva +  "]";
	}
	
}

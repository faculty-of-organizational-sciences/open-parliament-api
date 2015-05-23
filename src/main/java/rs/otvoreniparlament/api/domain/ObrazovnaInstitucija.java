package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "obrazovnainstitucija")

public class ObrazovnaInstitucija {

	private Integer obrazovnaInstitucijaID;
	private String imeObrazovneInstitucije;
	private String univerzitet;
	private Integer mestoID;
	
	public ObrazovnaInstitucija () {
		
	}
	
	public ObrazovnaInstitucija(Integer obrazovnaInstitucijaID, String imeObrazovneInstitucije, String univerzitet, Integer mestoID) {
		this.obrazovnaInstitucijaID = obrazovnaInstitucijaID;
		this.imeObrazovneInstitucije = imeObrazovneInstitucije;
		this.univerzitet = univerzitet;
		this.mestoID = mestoID;
	}

	@Id
	@Column(name = "idobrazovnainstitucija")
	public Integer getObrazovnaInstitucijaID() {
		return obrazovnaInstitucijaID;
	}

	public void setObrazovnaInstitucijaID(Integer obrazovnaInstitucijaID) {
		this.obrazovnaInstitucijaID = obrazovnaInstitucijaID;
	}

	@Basic
	@Column(name = "ime")
	public String getImeObrazovneInstitucije() {
		return imeObrazovneInstitucije;
	}

	public void setImeObrazovneInstitucije(String imeObrazovneInstitucije) {
		this.imeObrazovneInstitucije = imeObrazovneInstitucije;
	}

	@Basic
	@Column(name = "univerzitet")
	public String getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(String univerzitet) {
		this.univerzitet = univerzitet;
	}

	@Basic
	@Column(name = "idmesta")
	public Integer getMestoID() {
		return mestoID;
	}

	public void setMestoID(Integer mestoID) {
		this.mestoID = mestoID;
	}
	
	
	public String toString() {
		return "ObrazovnaInstitucija [obrazovnaInstitucijaID=" + obrazovnaInstitucijaID + ", ime=" + imeObrazovneInstitucije
				+ ", univerzitet=" + univerzitet + ", mestoID=" + mestoID + "]";
	}
}

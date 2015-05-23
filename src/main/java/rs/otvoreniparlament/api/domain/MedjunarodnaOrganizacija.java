package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "medjunarodnaorganizacija")
public class MedjunarodnaOrganizacija {

	private Integer medjunarodnaOrganizacijaID;
	private String ime;
	private Integer drzavaID;

	public MedjunarodnaOrganizacija() {
	}

	public MedjunarodnaOrganizacija(Integer medjunarodnaOrganizacijaID,
			String ime, Integer drzavaID) {
		this.medjunarodnaOrganizacijaID = medjunarodnaOrganizacijaID;
		this.ime = ime;
		this.drzavaID = drzavaID;
	}

	@Id
	@Column(name = "idmedjunarodnaorganizacija")
	public Integer getMedjunarodnaOrganizacijaID() {
		return medjunarodnaOrganizacijaID;
	}

	public void setMedjunarodnaOrganizacijaID(Integer medjunarodnaOrganizacijaID) {
		this.medjunarodnaOrganizacijaID = medjunarodnaOrganizacijaID;
	}

	@Basic
	@Column(name = "ime")
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
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
		return "MedjunarodnaOrganizacija [medjunarodnaOrganizacijaID="
				+ medjunarodnaOrganizacijaID + ", ime=" + ime + ", drzavaID="
				+ drzavaID + "]";
	}

}

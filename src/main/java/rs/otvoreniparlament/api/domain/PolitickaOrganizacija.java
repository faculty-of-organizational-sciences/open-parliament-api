package rs.otvoreniparlament.api.domain;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "politickaorganizacija")
public class PolitickaOrganizacija {

	private Integer politickaOrganizacijaID;
	private String ime;
	private Date datumOsnivanja;
	private String opis;
	private Integer tipPolitickeOrganizacijeID;
	private Integer mestoID;
	private Integer listaID;
	private Date datumUcestvovanjaListeNaIzborima;
	private Integer koalicijaID;
	private Date datumOsnivanjaKoalicije;

	public PolitickaOrganizacija() {
	}

	public PolitickaOrganizacija(Integer politickaOrganizacijaID, String ime,
			Date datumOsnivanja, String opis,
			Integer tipPolitickeOrganizacijeID, Integer mestoID,
			Integer listaID, Date datumUcestvovanjaListeNaIzborima,
			Integer koalicijaID, Date datumOsnivanjaKoalicije) {
		this.politickaOrganizacijaID = politickaOrganizacijaID;
		this.ime = ime;
		this.datumOsnivanja = datumOsnivanja;
		this.opis = opis;
		this.tipPolitickeOrganizacijeID = tipPolitickeOrganizacijeID;
		this.mestoID = mestoID;
		this.listaID = listaID;
		this.datumUcestvovanjaListeNaIzborima = datumUcestvovanjaListeNaIzborima;
		this.koalicijaID = koalicijaID;
		this.datumOsnivanjaKoalicije = datumOsnivanjaKoalicije;
	}

	@Id
	@Column(name = "idpolitickaorganizacija")
	public Integer getPolitickaOrganizacijaID() {
		return politickaOrganizacijaID;
	}

	public void setPolitickaOrganizacijaID(Integer politickaOrganizacijaID) {
		this.politickaOrganizacijaID = politickaOrganizacijaID;
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
	@Column(name = "datumosnivanja")
	public Date getDatumOsnivanja() {
		return datumOsnivanja;
	}

	public void setDatumOsnivanja(Date datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}

	@Basic
	@Column(name = "opis")
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Basic
	@Column(name = "idtipapolitickeorganizacije")
	public Integer getTipPolitickeOrganizacijeID() {
		return tipPolitickeOrganizacijeID;
	}

	public void setTipPolitickeOrganizacijeID(Integer tipPolitickeOrganizacijeID) {
		this.tipPolitickeOrganizacijeID = tipPolitickeOrganizacijeID;
	}

	@Basic
	@Column(name = "idmesta")
	public Integer getMestoID() {
		return mestoID;
	}

	public void setMestoID(Integer mestoID) {
		this.mestoID = mestoID;
	}

	@Basic
	@Column(name = "idliste")
	public Integer getListaID() {
		return listaID;
	}

	public void setListaID(Integer listaID) {
		this.listaID = listaID;
	}

	@Basic
	@Column(name = "datumucestvovanjalistenaizborima")
	public Date getDatumUcestvovanjaListeNaIzborima() {
		return datumUcestvovanjaListeNaIzborima;
	}

	public void setDatumUcestvovanjaListeNaIzborima(
			Date datumUcestvovanjaListeNaIzborima) {
		this.datumUcestvovanjaListeNaIzborima = datumUcestvovanjaListeNaIzborima;
	}

	@Basic
	@Column(name = "idkoalicije")
	public Integer getKoalicijaID() {
		return koalicijaID;
	}

	public void setKoalicijaID(Integer koalicijaID) {
		this.koalicijaID = koalicijaID;
	}

	@Basic
	@Column(name = "datumosnivanjakoalicije")
	public Date getDatumOsnivanjaKoalicije() {
		return datumOsnivanjaKoalicije;
	}

	public void setDatumOsnivanjaKoalicije(Date datumOsnivanjaKoalicije) {
		this.datumOsnivanjaKoalicije = datumOsnivanjaKoalicije;
	}

	@Override
	public String toString() {
		return "PolitickaOrganizacija [politickaOrganizacijaID="
				+ politickaOrganizacijaID + ", ime=" + ime
				+ ", datumOsnivanja=" + datumOsnivanja + ", opis=" + opis
				+ ", tipPolitickeOrganizacijeID=" + tipPolitickeOrganizacijeID
				+ ", mestoID=" + mestoID + ", listaID=" + listaID
				+ ", datumUcestvovanjaListeNaIzborima="
				+ datumUcestvovanjaListeNaIzborima + ", koalicijaID="
				+ koalicijaID + ", datumOsnivanjaKoalicije="
				+ datumOsnivanjaKoalicije + "]";
	}

}
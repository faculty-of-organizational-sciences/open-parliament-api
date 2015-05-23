package rs.otvoreniparlament.api.domain;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "lista")
public class Lista {

	private Integer listaID;
	private Date datumUcestvovanjaListeNaIzborima;
	private String naziv;

	public Lista() {
	}

	public Lista(Integer listaID, Date datumUcestvovanjaListeNaIzborima,
			String naziv) {
		this.listaID = listaID;
		this.datumUcestvovanjaListeNaIzborima = datumUcestvovanjaListeNaIzborima;
		this.naziv = naziv;
	}

	@Id
	@Column(name = "idlista")
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
	@Column(name = "naziv")
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "Lista [listaID=" + listaID
				+ ", datumUcestvovanjaListeNaIzborima="
				+ datumUcestvovanjaListeNaIzborima + ", naziv=" + naziv + "]";
	}

}

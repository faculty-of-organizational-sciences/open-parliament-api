package rs.otvoreniparlament.api.domain;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "imovinskakarta")
public class ImovinskaKarta {

	private Integer imovinskaKartaID;
	private Date datumPredaje;
	private String link;
	private Integer poslanikID;

	public ImovinskaKarta() {
	}

	public ImovinskaKarta(Integer imovinskaKartaID, Date datumPredaje,
			String link, Integer poslanikID) {
		this.imovinskaKartaID = imovinskaKartaID;
		this.datumPredaje = datumPredaje;
		this.link = link;
		this.poslanikID = poslanikID;
	}

	@Id
	@Column(name = "idimovinskakarta")
	public Integer getImovinskaKartaID() {
		return imovinskaKartaID;
	}

	public void setImovinskaKartaID(Integer imovinskaKartaID) {
		this.imovinskaKartaID = imovinskaKartaID;
	}

	@Basic
	@Column(name = "datumpredaje")
	public Date getDatumPredaje() {
		return datumPredaje;
	}

	public void setDatumPredaje(Date datumPredaje) {
		this.datumPredaje = datumPredaje;
	}

	@Basic
	@Column(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Basic
	@Column(name = "idposlanika")
	public Integer getPoslanikID() {
		return poslanikID;
	}

	public void setPoslanikID(Integer poslanikID) {
		this.poslanikID = poslanikID;
	}

	@Override
	public String toString() {
		return "ImovinskaKarta [imovinskaKartaID=" + imovinskaKartaID
				+ ", datumPredaje=" + datumPredaje + ", link=" + link
				+ ", poslanikID=" + poslanikID + "]";
	}

}

package rs.otvoreniparlament.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mesto")
public class Town {

	@Id
	@Column(name = "idmesto")
	private Integer townID;

	@Column(name = "ime")
	private String name;

	@Column(name = "udaljenostodbeograda")
	private Integer distanceFromBelgrade;

	@ManyToOne
	@JoinColumn(name = "idokruga")
	private Region region;

	@ManyToOne
	@JoinColumn(name = "iddrzave")
	private Country country;

	public Town() {
	}

	public Town(Integer townID, String name, Integer distanceFromBelgrade,
			Region region, Country country) {
		this.townID = townID;
		this.name = name;
		this.distanceFromBelgrade = distanceFromBelgrade;
		this.region = region;
		this.country = country;
	}

	public Integer getTownID() {
		return townID;
	}

	public void setTownID(Integer townID) {
		this.townID = townID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDistanceFromBelgrade() {
		return distanceFromBelgrade;
	}

	public void setDistanceFromBelgrade(Integer distanceFromBelgrade) {
		this.distanceFromBelgrade = distanceFromBelgrade;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Town [townID=" + townID + ", name=" + name
				+ ", distanceFromBelgrade=" + distanceFromBelgrade
				+ ", region=" + region + ", country=" + country + "]";
	}

}

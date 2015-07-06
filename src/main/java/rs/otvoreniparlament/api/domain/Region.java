package rs.otvoreniparlament.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "okrug")
public class Region {

	@Id
	@Column(name = "idokrug")
	private Integer regionID;

	@Column(name = "ime")
	private String name;

	public Region() {
	}

	public Region(Integer regionID, String name) {
		this.regionID = regionID;
		this.name = name;
	}

	public Integer getRegionID() {
		return regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Region [regionID=" + regionID + ", name=" + name + "]";
	}

}

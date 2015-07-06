package rs.otvoreniparlament.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drzava")
public class Country {

	@Id
	@Column(name = "iddrzava")
	private Integer countryID;

	@Column(name = "ime")
	private String name;

	@OneToMany()
	private List<FriendshipGroup> friendshipGroup;

	public Country() {
	}

	public Country(Integer countryID, String name,
			List<FriendshipGroup> friendshipGroup) {
		this.countryID = countryID;
		this.name = name;
		this.friendshipGroup = friendshipGroup;
	}

	public Integer getCountryID() {
		return countryID;
	}

	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FriendshipGroup> getFriendshipGroup() {
		return friendshipGroup;
	}

	public void setFriendshipGroup(List<FriendshipGroup> friendshipGroup) {
		this.friendshipGroup = friendshipGroup;
	}

	@Override
	public String toString() {
		return "Country [countryID=" + countryID + ", name=" + name
				+ ", friendshipGroup=" + friendshipGroup + "]";
	}

}

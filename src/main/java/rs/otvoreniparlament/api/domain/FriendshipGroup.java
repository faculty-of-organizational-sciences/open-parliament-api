package rs.otvoreniparlament.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupaprijateljstva")
public class FriendshipGroup {

	@Id
	@Column(name = "idgrupaprijateljstva")
	private Integer friendshipGroupID;

	@Column(name = "ime")
	private String name;

	@Column(name = "opis")
	private String about;

	public FriendshipGroup() {
	}

	public FriendshipGroup(Integer friendshipGroupID, String name, String about) {
		this.friendshipGroupID = friendshipGroupID;
		this.name = name;
		this.about = about;
	}

	public Integer getFriendshipGroupID() {
		return friendshipGroupID;
	}

	public void setFriendshipGroupID(Integer friendshipGroupID) {
		this.friendshipGroupID = friendshipGroupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "FriendshipGroup [friendshipGroupID=" + friendshipGroupID
				+ ", name=" + name + ", about=" + about + "]";
	}

}

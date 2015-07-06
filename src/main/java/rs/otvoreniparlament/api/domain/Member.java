package rs.otvoreniparlament.api.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "poslanik")
public class Member {

	@Id
	@Column(name = "idposlanik")
	private Integer memberID;

	// @Column(name = "rec_date")
	// private Timestamp rec_date;

	// u bazi je ovo tip enum
	// @Column(name = "active")
	// private String active;

	@Column(name = "ime")
	private String name;

	@Column(name = "prezime")
	private String lastName;

	@Column(name = "datumrodjenja")
	private Date dateOfBirth;

	// pol: 0 muski, 1 zenski
	@Column(name = "pol")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "biografija")
	private String biography;

	// @Column(name = "img_url")
	// private String img_url;

	// @Column(name = "img_mime_type")
	// private String img_mime_type;

	@OneToOne
	private Town placeOfBirth;

	@OneToOne
	private Town placeOfResidence;

	@OneToOne
	private PartyList list;

	// @Column(name = "datumucestvovanjalistenaizborima")
	// private Date listSelectionParticipationDate;

	@OneToOne
	private Assembly currentAssembly;

	public Member() {

	}

	public Member(Integer memberID, String name, String lastName, Date dateOfBirth, String gender, String email,
			String biography, Town placeOfBirth, Town placeOfResidence, PartyList list, Assembly currentAssembly) {
		super();
		this.memberID = memberID;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.biography = biography;
		this.placeOfBirth = placeOfBirth;
		this.placeOfResidence = placeOfResidence;
		this.list = list;
		this.currentAssembly = currentAssembly;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Town getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(Town placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Town getPlaceOfResidence() {
		return placeOfResidence;
	}

	public void setPlaceOfResidence(Town placeOfResidence) {
		this.placeOfResidence = placeOfResidence;
	}

	public PartyList getList() {
		return list;
	}

	public void setList(PartyList list) {
		this.list = list;
	}

	public Assembly getCurrentAssembly() {
		return currentAssembly;
	}

	public void setCurrentAssembly(Assembly currentAssembly) {
		this.currentAssembly = currentAssembly;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", name=" + name + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", email=" + email + ", biography=" + biography
				+ ", placeOfBirth=" + placeOfBirth + ", placeOfResidence=" + placeOfResidence + ", list=" + list
				+ ", currentAssembly=" + currentAssembly + "]";
	}

}
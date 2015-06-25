package rs.otvoreniparlament.api.domain;

import java.sql.Date;
import java.sql.Timestamp;

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

	@Column(name = "rec_date")
	private Timestamp rec_date;

	// u bazi je ovo tip enum
	@Column(name = "active")
	private String active;

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
	
	@Column(name = "img_url")
	private String img_url;

	@Column(name = "img_mime_type")
	private String img_mime_type;

	@OneToOne
	private Town placeOfBirth;
	
	@OneToOne
	private Town placeOfLiving;

	@OneToOne
	private PartieList list;

	@Column(name = "datumucestvovanjalistenaizborima")
	private Date listSelectionParticipationDate;

	@OneToOne
	private Gathering currentGathering;

	public Member() {

	}

	public Member(Integer memberID, Timestamp rec_date, String active,
			String name, String lastName, Date dateOfBirth, String gender,
			String email, String biography, String img_url,
			String img_mime_type, Town placeOfBirth, Town placeOfLiving,
			PartieList list, Date listSelectionParticipationDate,
			Gathering currentGathering) {
		this.memberID = memberID;
		this.rec_date = rec_date;
		this.active = active;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.biography = biography;
		this.img_url = img_url;
		this.img_mime_type = img_mime_type;
		this.placeOfBirth = placeOfBirth;
		this.placeOfLiving = placeOfLiving;
		this.list = list;
		this.listSelectionParticipationDate = listSelectionParticipationDate;
		this.currentGathering = currentGathering;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Timestamp getRec_date() {
		return rec_date;
	}

	public void setRec_date(Timestamp rec_date) {
		this.rec_date = rec_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getImg_mime_type() {
		return img_mime_type;
	}

	public void setImg_mime_type(String img_mime_type) {
		this.img_mime_type = img_mime_type;
	}

	public Town getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(Town placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Town getPlaceOfLiving() {
		return placeOfLiving;
	}

	public void setPlaceOfLiving(Town placeOfLiving) {
		this.placeOfLiving = placeOfLiving;
	}

	public PartieList getList() {
		return list;
	}

	public void setList(PartieList list) {
		this.list = list;
	}

	public Date getListSelectionParticipationDate() {
		return listSelectionParticipationDate;
	}

	public void setListSelectionParticipationDate(
			Date listSelectionParticipationDate) {
		this.listSelectionParticipationDate = listSelectionParticipationDate;
	}

	public Gathering getCurrentGathering() {
		return currentGathering;
	}

	public void setCurrentGathering(Gathering currentGathering) {
		this.currentGathering = currentGathering;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", rec_date=" + rec_date
				+ ", active=" + active + ", name=" + name + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", gender="
				+ gender + ", email=" + email + ", biography=" + biography
				+ ", img_url=" + img_url + ", img_mime_type=" + img_mime_type
				+ ", placeOfBirth=" + placeOfBirth + ", placeOfLiving="
				+ placeOfLiving + ", list=" + list
				+ ", listSelectionParticipationDate="
				+ listSelectionParticipationDate + ", currentGathering="
				+ currentGathering + "]";
	}

}

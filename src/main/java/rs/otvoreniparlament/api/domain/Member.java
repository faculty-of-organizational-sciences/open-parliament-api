package rs.otvoreniparlament.api.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "poslanik")
public class Member {

	@Id
	@Column(name = "idposlanik")
	private Integer id;

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

	@ManyToOne
	@JoinColumn(name = "idmestarodjenja")
	private Town placeOfBirth;

	@ManyToOne
	@JoinColumn(name = "idmestaprebivalista")
	private Town placeOfResidence;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "clanpolitickeorganizacije", joinColumns = @JoinColumn(name = "idposlanika") , inverseJoinColumns = @JoinColumn(name = "idpolitickeorganizacije") )
	private List<Party> parties;

	public Member() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Party> getParties() {
		return parties;
	}

	public void setParties(List<Party> parties) {
		this.parties = parties;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + id + ", name=" + name + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", email=" + email + ", biography=" + biography
				+ ", placeOfBirth=" + placeOfBirth + ", placeOfResidence=" + placeOfResidence + "]";
	}

}

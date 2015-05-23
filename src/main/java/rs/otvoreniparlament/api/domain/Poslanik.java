package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;
import java.sql.Timestamp;

@Entity()
@Table(name = "poslanik")
public class Poslanik {

	private Integer poslanikID;
	private Timestamp rec_date;

	// u bazi je ovo tip enum
	private char aktivan;

	private String imePoslanika;
	private String prezimePoslanika;

	private Date datumRodjenja;
	// pol: 0 muski, 1 zenski
	private boolean pol;
	private String email;
	private String biografija;
	private String img_url;
	private String img_mime_type;
	private Integer mestoRodjenjaID;
	private Integer mestoPrebivalistaID;
	private Integer listaID;

	private Date datumUcestvovanjaNaIzborima;

	private Integer trenutniSazivID;

	public Poslanik() {

	}

	public Poslanik(Integer poslanikID, Timestamp rec_date, char aktivan,
			String imePoslanika, String prezimePoslanika, Date datumRodjenja,
			boolean pol, String email, String biografija, String img_url,
			String img_mime_type, Integer mestoRodjenjaID,
			Integer mestoPRebivalistaID, Integer listaID, Date datumNaIzborima,
			Integer trenutniSazivID) {

		
		this.poslanikID = poslanikID;
		this.rec_date = rec_date;
		this.aktivan = aktivan;
		this.imePoslanika = imePoslanika;
		this.prezimePoslanika = prezimePoslanika;
		this.datumRodjenja = datumRodjenja;
		this.pol = pol;
		this.email = email;
		this.biografija = biografija;
		this.img_url = img_url;
		this.img_mime_type = img_mime_type;
		this.mestoRodjenjaID = mestoRodjenjaID;
		this.mestoPrebivalistaID = mestoPRebivalistaID;
		this.listaID = listaID;
		datumUcestvovanjaNaIzborima = datumNaIzborima;
		this.trenutniSazivID = trenutniSazivID;
	}

	@Id
	@Column(name = "idposlanik")
	public Integer getPoslanikID() {
		return poslanikID;
	}

	public void setPoslanikID(Integer poslanikID) {
		this.poslanikID = poslanikID;
	}

	@Basic
	@Column(name= "rec_date")
	public Timestamp getRec_date() {
		return rec_date;
	}

	public void setRec_date(Timestamp rec_date) {
		this.rec_date = rec_date;
	}

	@Basic
	@Column(name= "active")
	public char getAktivan() {
		return aktivan;
	}

	public void setAktivan(char aktivan) {
		this.aktivan = aktivan;
	}

	@Basic
	@Column(name= "ime")
	public String getImePoslanika() {
		return imePoslanika;
	}

	public void setImePoslanika(String imePoslanika) {
		this.imePoslanika = imePoslanika;
	}

	@Basic
	@Column(name= "prezime")
	public String getPrezimePoslanika() {
		return prezimePoslanika;
	}

	public void setPrezimePoslanika(String prezimePoslanika) {
		this.prezimePoslanika = prezimePoslanika;
	}

	@Basic
	@Column(name= "datumrodjenja")
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Basic
	@Column(name= "pol")
	public boolean isPol() {
		return pol;
	}

	public void setPol(boolean pol) {
		this.pol = pol;
	}

	@Basic
	@Column(name= "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name= "biografija")
	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

	@Basic
	@Column(name= "img_url")
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Basic
	@Column(name= "img_mime_type")
	public String getImg_mime_type() {
		return img_mime_type;
	}

	public void setImg_mime_type(String img_mime_type) {
		this.img_mime_type = img_mime_type;
	}

	@Basic
	@Column(name= "idmestarodjenja")
	public Integer getMestoRodjenjaID() {
		return mestoRodjenjaID;
	}

	public void setMestoRodjenjaID(Integer mestoRodjenjaID) {
		this.mestoRodjenjaID = mestoRodjenjaID;
	}

	@Basic
	@Column(name= "idmestaprebivalista")
	public Integer getMestoPrebivalistaID() {
		return mestoPrebivalistaID;
	}

	public void setMestoPrebivalistaID(Integer mestoPrebivalistaID) {
		this.mestoPrebivalistaID = mestoPrebivalistaID;
	}

	@Basic
	@Column(name= "idliste")
	public Integer getListaID() {
		return listaID;
	}

	public void setListaID(Integer listaID) {
		this.listaID = listaID;
	}

	@Basic
	@Column(name= "datumucestvovanjalistenaizborima")
	public Date getDatumUcestvovanjaNaIzborima() {
		return datumUcestvovanjaNaIzborima;
	}

	public void setDatumUcestvovanjaNaIzborima(Date datumUcestvovanjaNaIzborima) {
		this.datumUcestvovanjaNaIzborima = datumUcestvovanjaNaIzborima;
	}

	@Basic
	@Column(name= "idtrenutnogsaziva")
	public Integer getTrenutniSazivID() {
		return trenutniSazivID;
	}

	public void setTrenutniSazivID(Integer trenutniSazivID) {
		this.trenutniSazivID = trenutniSazivID;
	}

	@Override
	public String toString() {
		
		 return "Poslanik [poslanikID=" + poslanikID + ", rec_date=" + rec_date
				+ ", aktivan=" + aktivan + ", ime=" + imePoslanika + ", prezime=" + prezimePoslanika +
				", pol=" + pol +", datumRodj=" + datumRodjenja +", email=" + email +", biografija=" + biografija +"]";
	}

	
	
	

}

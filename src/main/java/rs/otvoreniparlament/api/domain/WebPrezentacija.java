package rs.otvoreniparlament.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "webprezentacija")
public class WebPrezentacija {

	private Integer webPrezentacijaID;
	private String ime;
	private String link;

	public WebPrezentacija() {
	}

	public WebPrezentacija(Integer webPrezentacijaID, String ime, String link) {
		this.webPrezentacijaID = webPrezentacijaID;
		this.ime = ime;
		this.link = link;
	}

	@Id
	@Column(name = "idwebprezentacija")
	public Integer getWebPrezentacijaID() {
		return webPrezentacijaID;
	}

	public void setWebPrezentacijaID(Integer webPrezentacijaID) {
		this.webPrezentacijaID = webPrezentacijaID;
	}

	@Basic
	@Column(name = "ime")
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Basic
	@Column(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "WebPrezentacija [webPrezentacijaID=" + webPrezentacijaID
				+ ", ime=" + ime + ", link=" + link + "]";
	}

}

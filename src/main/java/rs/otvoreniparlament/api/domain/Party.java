package rs.otvoreniparlament.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "politickaorganizacija")
public class Party {

	@Id
	@Column(name = "idpolitickaorganizacija")
	private Integer partyId;

	@Column(name = "ime")
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "clanpolitickeorganizacije", joinColumns = @JoinColumn(name = "idpolitickeorganizacije") , inverseJoinColumns = @JoinColumn(name = "idposlanika") )
	private List<Member> members;

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", name=" + name + "]";
	}

}

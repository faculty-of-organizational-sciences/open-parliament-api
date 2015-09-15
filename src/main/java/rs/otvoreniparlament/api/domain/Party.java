package rs.otvoreniparlament.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import rs.otvoreniparlament.api.domain.meta.RestServiceName;

@Entity
@Table(name = "politickaorganizacija")
@RestServiceName("parties")
public class Party {

	@Id
	@Column(name = "idpolitickaorganizacija")
	private Integer id;

	@Column(name = "ime")
	private String name;
	
	@ManyToMany
	@JoinTable(name = "clanpolitickeorganizacije", joinColumns = @JoinColumn(name = "idpolitickeorganizacije") , inverseJoinColumns = @JoinColumn(name = "idposlanika") )
	private List<Member> members;

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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Party [partyId=" + id + ", name=" + name + "]";
	}

}

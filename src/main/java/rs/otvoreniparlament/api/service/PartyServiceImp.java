package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public class PartyServiceImp implements PartyService {
	
	protected PartyDao pd = new PartyDao();

	@Override
	public List<Party> getParties(int page, int limit, String sort) {
		return pd.getParties(page, limit, sort);
	}

	@Override
	public Party getParty(int id) {
		return pd.getParty(id);
	}

	@Override
	public List<Member> getPartyMembers(int id) {		
		return pd.getParty(id).getMembers();
	}
}

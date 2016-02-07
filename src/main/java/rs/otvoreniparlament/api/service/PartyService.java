package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public interface PartyService {

	ServiceResponse<Party> getParties(int page, int limit, String sort, String query);

	Party getParty(int id);

	List<Member> getPartyMembers(int id, int limit, int page);
}

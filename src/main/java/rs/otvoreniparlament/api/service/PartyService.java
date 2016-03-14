package rs.otvoreniparlament.api.service;


import rs.otvoreniparlament.api.domain.Party;

public interface PartyService {

	ServiceResponse<Party> getParties(int page, int limit, String sort, String query);

	Party getParty(int id);

}

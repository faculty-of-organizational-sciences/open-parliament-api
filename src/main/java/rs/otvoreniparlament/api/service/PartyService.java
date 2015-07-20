package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.domain.Party;

public interface PartyService {

	List<Party> getParties(int page, int limit, String sort);
}

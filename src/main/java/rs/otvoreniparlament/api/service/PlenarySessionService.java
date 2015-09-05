package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.domain.PlenarySession;

public interface PlenarySessionService {

	public List<PlenarySession> getPlenarySessions(int limit, int page);

	public PlenarySession getPlenarySession(int id);

}

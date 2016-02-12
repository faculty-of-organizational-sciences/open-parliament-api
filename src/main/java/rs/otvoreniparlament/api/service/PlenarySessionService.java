package rs.otvoreniparlament.api.service;

import rs.otvoreniparlament.api.domain.PlenarySession;

public interface PlenarySessionService {

	public ServiceResponse<PlenarySession> getPlenarySessions(int limit, int page);

	public PlenarySession getPlenarySession(int id);

}

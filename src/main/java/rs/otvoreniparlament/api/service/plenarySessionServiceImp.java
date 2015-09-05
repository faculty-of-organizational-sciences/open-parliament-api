package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.PlenarySessionDao;
import rs.otvoreniparlament.api.domain.PlenarySession;

public class plenarySessionServiceImp implements PlenarySessionService {

	protected PlenarySessionDao psd = new PlenarySessionDao();

	@Override
	public List<PlenarySession> getPlenarySessions(int limit, int page) {
		return psd.getPlenarySessions(limit, page);
	}

	@Override
	public PlenarySession getPlenarySession(int id) {
		return psd.getPlenarySession(id);
	}

}

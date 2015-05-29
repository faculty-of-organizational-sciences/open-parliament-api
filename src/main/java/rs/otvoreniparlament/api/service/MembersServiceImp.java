package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Poslanik;

public class MembersServiceImp implements MembersService {

	@Override
	public List<Poslanik> getMembers() {
		return new MembersDao().getMembers();
	}

	
}

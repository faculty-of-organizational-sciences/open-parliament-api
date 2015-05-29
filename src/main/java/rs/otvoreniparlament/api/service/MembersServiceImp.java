package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Poslanik;

public class MembersServiceImp implements MembersService {

	protected MembersDao md;

	@Override
	public List<Poslanik> getMembers() {
		md = new MembersDao();
		return md.getMembers();
	}

}

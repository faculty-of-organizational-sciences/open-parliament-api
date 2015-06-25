package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;

public class MembersServiceImp implements MembersService {

	protected MembersDao md = new MembersDao();

	@Override
	public List<Member> getMembers(int page, int limit) {
		return md.getMembers(page, limit);
	}

}

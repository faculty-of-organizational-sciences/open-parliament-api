package rs.otvoreniparlament.api.service;

import rs.otvoreniparlament.api.domain.Member;

public interface MembersService {

	ServiceResponse<Member> getMembers(int page, int limit, String sort, String query);
	
	Member getMember(int id);

	ServiceResponse<Member> getPartyMembers(int id, int limit, int page);

}

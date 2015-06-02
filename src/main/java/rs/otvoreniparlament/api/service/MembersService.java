package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.domain.Poslanik;

public interface MembersService {
	
	List<Poslanik> getMembers(int page, int limit);
	

}

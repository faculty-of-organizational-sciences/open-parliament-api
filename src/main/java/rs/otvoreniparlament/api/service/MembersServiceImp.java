package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class MembersServiceImp implements MembersService {

	protected MembersDao md = new MembersDao();
	protected ElasticSearchService es = new ElasticSearchService();
	@Override
	public List<Member> getMembers(int page, int limit, String sort, String query) {
		if (ElasticClient.connectionStatus.equals("disconnected")){
			return md.getMembers(page, limit, sort, query);
		}else {
			es.searchQuery(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, query);
			return null; //transform hits in list!
		}
		
	}
	
	@Override
	public Member getMember(int id) {		
		return md.getMember(id);
	}
}

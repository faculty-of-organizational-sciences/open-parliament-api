package rs.otvoreniparlament.api.service;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.util.MembersConvertor;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class MembersServiceImp implements MembersService {

	protected MembersDao md = new MembersDao();
	protected ElasticSearchService es = new ElasticSearchService();
	
	@Override
	public ServiceResponse<Member> getMembers(int page, int limit, String sort, String query) {
		ServiceResponse<Member> response = new ServiceResponse<>();
		
		if (ElasticClient.connectionStatus == false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			response.setRecords(md.getMembers(page, limit, sort, query));
			response.setTotalHits(-1);
			
		}else {
			SearchResponse searchResponse = es.searchQuery(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, query, limit, page);
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(MembersConvertor.convertToMembers(searchResponse));
		}
		return response;
	}
	
	@Override
	public Member getMember(int id) {		
		ServiceResponse<Member> response = new ServiceResponse<>();
		
		if (ElasticClient.connectionStatus == false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			return md.getMember(id);
			
		}else {
			SearchResponse searchResponse = es.searchSpecificID(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, "id", id);
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			return MembersConvertor.convertToMember(searchResponse);
		}
	}
	
}

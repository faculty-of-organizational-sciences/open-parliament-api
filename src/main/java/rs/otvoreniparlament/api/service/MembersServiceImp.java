package rs.otvoreniparlament.api.service;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.util.MembersConvertor;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class MembersServiceImp implements MembersService {

	protected MembersDao md = new MembersDao();

	@Override
	public ServiceResponse<Member> getMembers(int page, int limit, String sort, String query) {
		
		ServiceResponse<Member> response = new ServiceResponse<>();

		if (!ElasticClient.getInstance().isConnectionStatus()) {
			
			response.setRecords(md.getMembers(page, limit, sort, query));
			response.setTotalHits(md.getTotalCount(query));

		} else {
			SearchResponse searchResponse = ElasticSearchService.searchQueryWihtFields(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, query, limit,	page);
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(MembersConvertor.convertToMembers(searchResponse));

		}
		return response;
	}

	@Override
	public Member getMember(int id) {
		
		ServiceResponse<Member> response = new ServiceResponse<>();

		if (!ElasticClient.getInstance().isConnectionStatus()) {
			return md.getMember(id);

		} else {
			SearchResponse searchResponse = ElasticSearchService.searchSpecificID(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, "id", String.valueOf(id));
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			
			if (searchResponse.getHits().getTotalHits() == 0) {
				return null;
			}
			
			return MembersConvertor.convertToMember(searchResponse.getHits().getAt(0));
		}
	}

}

package rs.otvoreniparlament.api.service.impl;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.IndexName;
import rs.otvoreniparlament.api.index.IndexType;
import rs.otvoreniparlament.api.index.search.ElasticMembersSearchService;
import rs.otvoreniparlament.api.service.MembersService;
import rs.otvoreniparlament.api.service.ServiceResponse;
import rs.otvoreniparlament.api.service.util.MembersConvertor;
import rs.otvoreniparlament.api.service.util.PartyConvertor;

public class MembersServiceImp implements MembersService {

	private MembersDao md;
	private ElasticMembersSearchService elasticSearch;
	
	public MembersServiceImp() {
		md = new MembersDao();
		elasticSearch = new ElasticMembersSearchService();
	}

	@Override
	public ServiceResponse<Member> getMembers(int page, int limit, String sort, String query) {
		
		ServiceResponse<Member> response = new ServiceResponse<>();

		if (!ElasticClient.getInstance().isConnectionStatus()) {
			
			response.setRecords(md.getMembers(page, limit, sort, query));
			response.setTotalHits(md.getTotalCount(query));

		} else {
			SearchResponse searchResponse = elasticSearch.searchQueryWihtFields(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, query, limit, page);
			
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
			SearchResponse searchResponse = elasticSearch.searchSpecificID(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, "id", String.valueOf(id));
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			
			if (searchResponse.getHits().getTotalHits() == 0) {
				return null;
			}
			
			return MembersConvertor.convertToMember(searchResponse.getHits().getAt(0));
		}
	}
	
	@Override
	public ServiceResponse<Member> getPartyMembers(int id, int limit, int page) {

		ServiceResponse<Member> response = new ServiceResponse<>();

		if (!ElasticClient.getInstance().isConnectionStatus()) {

			response.setRecords(md.getPartyMembers(id, limit, page));
			response.setTotalHits(md.getPartyMembersTotalCount(id));

		} else {
			SearchResponse searchResponse = elasticSearch.searchSpecificPartyMember(IndexName.PARTY_INDEX,
					IndexType.PARTY_TYPE, id, limit, page);

			response.setRecords(PartyConvertor.convertToPartyMembers(searchResponse));
			response.setTotalHits(searchResponse.getHits().getTotalHits());
		}
		return response;
	}

}

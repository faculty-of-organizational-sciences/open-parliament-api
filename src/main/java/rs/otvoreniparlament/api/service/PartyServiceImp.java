package rs.otvoreniparlament.api.service;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.util.PartyConvertor;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class PartyServiceImp implements PartyService {
	
	protected PartyDao pd = new PartyDao();

	@Override
	public ServiceResponse<Party> getParties(int page, int limit, String sort, String query) {
		
		ServiceResponse<Party> response = new ServiceResponse<>();
		
		if (!ElasticClient.getInstance().isConnectionStatus()){
			
			response.setRecords(pd.getParties(page, limit, sort, query));
			response.setTotalHits(pd.getPartiesTotalCount(query));
			
		}else {
			
			SearchResponse searchResponse = ElasticSearchService.searchQuery(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE, query, limit, page);
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(PartyConvertor.convertToParties(searchResponse));
			
		}
		return response;
	}

	@Override
	public Party getParty(int id) {
		
		if (!ElasticClient.getInstance().isConnectionStatus()){
			return pd.getParty(id);
		}else {
			SearchResponse searchResponse =ElasticSearchService.searchSpecificID(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE ,"party-id", String.valueOf(id));
			
			if (searchResponse.getHits().getTotalHits() == 0) {
				return null;
			}
			
			return PartyConvertor.convertToParty(searchResponse.getHits().getAt(0));
		}
	}

	@Override
	public ServiceResponse<Member> getPartyMembers(int id, int limit, int page) {
		
		ServiceResponse<Member> response = new ServiceResponse<>();
		
		if (!ElasticClient.getInstance().isConnectionStatus()){
			
			response.setRecords( pd.getPartyMembers(id, limit, page));
			response.setTotalHits(pd.getPartyMembersTotalCount(id));
			
		}else {
			SearchResponse searchResponse =ElasticSearchService.searchSpecificPartyMember(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE, id, limit, page);
			
			response.setRecords(PartyConvertor.convertToPartyMembers(searchResponse));
			response.setTotalHits(searchResponse.getHits().getTotalHits());
		}
		return response;
	}
}

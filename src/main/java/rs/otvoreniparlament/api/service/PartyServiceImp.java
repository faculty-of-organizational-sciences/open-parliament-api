package rs.otvoreniparlament.api.service;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.util.PartyConvertor;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class PartyServiceImp implements PartyService {
	protected ElasticSearchService es  =new ElasticSearchService();
	protected PartyDao pd = new PartyDao();

	@Override
	public ServiceResponse<Party> getParties(int page, int limit, String sort, String query) {
		ServiceResponse<Party> response = new ServiceResponse<>();
		if (ElasticClient.connectionStatus== false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			response.setRecords(pd.getParties(page, limit, sort, query));
		}else {
			SearchResponse searchResponse = es.searchQuery(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE, query, limit);
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(PartyConvertor.convertToParties(searchResponse));
		}
		return response;
	}

	@Override
	public Party getParty(int id) {
		if (ElasticClient.connectionStatus == false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			return pd.getParty(id);
		}else {
			SearchResponse searchResponse =es.searchSpecificID(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE ,"party-id", id);
			return PartyConvertor.convertToParty(searchResponse);
		}
	}

	@Override
	public List<Member> getPartyMembers(int id, int limit, int page) {		
		if (ElasticClient.connectionStatus == false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			return pd.getPartyMembers(id, limit, page);
		}else {
			es.searchQuery(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE ,"", limit);
			return null; //transform hits in list!
		}
	}
}

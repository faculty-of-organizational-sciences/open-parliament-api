package rs.otvoreniparlament.api.service.impl;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.IndexName;
import rs.otvoreniparlament.api.index.IndexType;
import rs.otvoreniparlament.api.index.search.ElasticPartiesSearchService;
import rs.otvoreniparlament.api.service.PartyService;
import rs.otvoreniparlament.api.service.ServiceResponse;
import rs.otvoreniparlament.api.service.util.PartyConvertor;

public class PartyServiceImp implements PartyService {

	private PartyDao pd;
	private ElasticPartiesSearchService elasticSearch;

	public PartyServiceImp() {
		pd = new PartyDao();
		elasticSearch = new ElasticPartiesSearchService();
	}

	@Override
	public ServiceResponse<Party> getParties(int page, int limit, String sort, String query) {

		ServiceResponse<Party> response = new ServiceResponse<>();

		if (Settings.getInstance().config.getElasticConfig().isUsingElastic() && 
				ElasticClient.getInstance().isConnectionStatus()) {

			SearchResponse searchResponse = elasticSearch.searchQuery(IndexName.PARTY_INDEX,
					IndexType.PARTY_TYPE, query, limit, page);
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(PartyConvertor.convertToParties(searchResponse));
		} else {
			response.setRecords(pd.getParties(page, limit, sort, query));
			response.setTotalHits(pd.getPartiesTotalCount(query));
		}
		return response;
	}

	@Override
	public Party getParty(int id) {

		if (Settings.getInstance().config.getElasticConfig().isUsingElastic() && 
				ElasticClient.getInstance().isConnectionStatus()) {
			SearchResponse searchResponse = elasticSearch.searchSpecificID(IndexName.PARTY_INDEX,
					IndexType.PARTY_TYPE, "party-id", String.valueOf(id));
			
			if (searchResponse.getHits().getTotalHits() == 0) {
				return null;
			}
			
			return PartyConvertor.convertToParty(searchResponse.getHits().getAt(0));
		} else {
			return pd.getParty(id);
		}
	}

}

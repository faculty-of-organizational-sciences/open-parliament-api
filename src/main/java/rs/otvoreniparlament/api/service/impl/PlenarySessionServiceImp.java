package rs.otvoreniparlament.api.service.impl;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.dao.PlenarySessionDao;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.index.IndexName;
import rs.otvoreniparlament.api.index.IndexType;
import rs.otvoreniparlament.api.index.search.ElasticPlenarySessionSearchService;
import rs.otvoreniparlament.api.service.PlenarySessionService;
import rs.otvoreniparlament.api.service.ServiceResponse;
import rs.otvoreniparlament.api.service.util.PlenarySessionConverter;

public class PlenarySessionServiceImp implements PlenarySessionService {

	private PlenarySessionDao psd;
	private ElasticSearchService elasticSearch;
	
	public PlenarySessionServiceImp() {
		psd = new PlenarySessionDao();
		elasticSearch = new ElasticPlenarySessionSearchService();
	}

	@Override
	public ServiceResponse<PlenarySession> getPlenarySessions(int limit, int page) {

		ServiceResponse<PlenarySession> response = new ServiceResponse<>();

		if (!ElasticClient.getInstance().isConnectionStatus()) {

			response.setRecords(psd.getPlenarySessions(limit, page));
			response.setTotalHits(psd.getTotalCount());

		} else {
			SearchResponse searchResponse = elasticSearch.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE, "", limit, page);

			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(PlenarySessionConverter.convertToSession(searchResponse));
		}
		return response;
	}

	@Override
	public PlenarySession getPlenarySession(int id) {

		if (!ElasticClient.getInstance().isConnectionStatus()) {
			return psd.getPlenarySession(id);
		} else {
			SearchResponse searchResponse = elasticSearch.searchSpecificID(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE, "id", String.valueOf(id));

			if (searchResponse.getHits().getTotalHits() == 0) {
				return null;
			}

			return PlenarySessionConverter.convertToPlenarySession(searchResponse.getHits().getAt(0));
		}
	}

}

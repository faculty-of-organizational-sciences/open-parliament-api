package rs.otvoreniparlament.api.service;

import org.elasticsearch.action.search.SearchResponse;
import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.dao.PlenarySessionDao;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.util.PlenarySessionConverter;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class PlenarySessionServiceImp implements PlenarySessionService {

	protected ElasticSearchService es = new ElasticSearchService();
	protected PlenarySessionDao psd = new PlenarySessionDao();

	@Override
	public ServiceResponse<PlenarySession> getPlenarySessions(int limit, int page) {
		ServiceResponse<PlenarySession> response = new ServiceResponse<>();
		if (ElasticClient.connectionStatus== false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			response.setRecords( psd.getPlenarySessions(limit, page));
		}else {
			SearchResponse searchResponse = es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , "", limit);
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(PlenarySessionConverter.convertToSession(searchResponse));
		}
		return response;
	}

	@Override
	public PlenarySession getPlenarySession(int id) {
		if (ElasticClient.connectionStatus== false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			return psd.getPlenarySession(id);
		}else {
			
		SearchResponse searchResponse = es.searchSpecificID(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE ,"", id);
			return PlenarySessionConverter.convertToPlenarySession(searchResponse);
		}
	}

}

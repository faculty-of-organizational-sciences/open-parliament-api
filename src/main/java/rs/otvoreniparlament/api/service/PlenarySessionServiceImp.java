package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.PlenarySessionDao;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class PlenarySessionServiceImp implements PlenarySessionService {

	protected ElasticSearchService es = new ElasticSearchService();
	protected PlenarySessionDao psd = new PlenarySessionDao();

	@Override
	public List<PlenarySession> getPlenarySessions(int limit, int page) {
		if (ElasticClient.connectionStatus== false){
			return psd.getPlenarySessions(limit, page);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , "");
			return null; //transform hits in list!
		}
	}

	@Override
	public PlenarySession getPlenarySession(int id) {
		if (ElasticClient.connectionStatus== false){
			return psd.getPlenarySession(id);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , String.valueOf(id));
			return null; //transform hits in list!
		}
	}

}

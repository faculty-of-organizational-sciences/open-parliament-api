package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.PlenarySessionDao;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class plenarySessionServiceImp implements PlenarySessionService {

	protected ElasticSearchService es = new ElasticSearchService();
	protected PlenarySessionDao psd = new PlenarySessionDao();

	@Override
	public List<PlenarySession> getPlenarySessions(int limit, int page) {
		if (ElasticClient.connectionStatus.equals("disconnected")){
			return psd.getPlenarySessions(limit, page);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , "");
			return null; //transform hits in list!
		}
	}

	@Override
	public PlenarySession getPlenarySession(int id) {
		if (ElasticClient.connectionStatus.equals("disconnected")){
			return psd.getPlenarySession(id);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , String.valueOf(id));
			return null; //transform hits in list!
		}
	}

}

package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class PartyServiceImp implements PartyService {
	protected ElasticSearchService es  =new ElasticSearchService();
	protected PartyDao pd = new PartyDao();

	@Override
	public List<Party> getParties(int page, int limit, String sort, String query) {
		if (ElasticClient.connectionStatus.equals("disconnected")){
			return pd.getParties(page, limit, sort, query);
		}else {
			es.searchQuery(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE, query);
			return null; //transform hits in list!
		}
	}

	@Override
	public Party getParty(int id) {
		if (ElasticClient.connectionStatus.equals("disconnected")){
			return pd.getParty(id);
		}else {
			es.searchQuery(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE , String.valueOf(id) );
			return null; //transform hits in list!
		}
	}

	@Override
	public List<Member> getPartyMembers(int id, int limit, int page) {		
		if (ElasticClient.connectionStatus.equals("disconnected")){
			return pd.getPartyMembers(id, limit, page);
		}else {
			es.searchQuery(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE ,"" );
			return null; //transform hits in list!
		}
	}
}

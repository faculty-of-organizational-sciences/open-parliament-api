package rs.otvoreniparlament.api.service;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.util.SpeechConverter;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class SpeechServiceImp implements SpeechService {

	protected ElasticSearchService es = new ElasticSearchService();
	protected SpeechDao sd = new SpeechDao();

	@Override
	public ServiceResponse<Speech> getMemberSpeeches(int id, int limit, int page, String qtext, String from, String to) {
		
		ServiceResponse<Speech> response = new ServiceResponse<>();
		
		if (!ElasticClient.getInstance().isConnectionStatus()){
			
			response.setRecords(sd.getMemberSpeeches(id, limit, page, qtext, from, to));
			response.setTotalHits(sd.getMemberSpeechesTotalCount(id, qtext, from, to));
			
		}else {
			SearchResponse searchResponse = es.searchSpecificListMember(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, id, limit,page, qtext, from, to);
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(SpeechConverter.convertToSpeeches(searchResponse));
		}
		return response;
	}

	@Override
	public Speech getSpeech(int id) {
		if (!ElasticClient.getInstance().isConnectionStatus()){
			return sd.getSpeech(id);
		}else {
			SearchResponse searchresponse= es.searchSpecificID(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, "speechid", id);
			return SpeechConverter.convertToSpeech(searchresponse);
		}
	}

	@Override
	public ServiceResponse<Speech> getSpeeches(int limit, int page) {
		
		ServiceResponse<Speech> response = new ServiceResponse<>();
		
		if (!ElasticClient.getInstance().isConnectionStatus()){
			
			response.setRecords(sd.getSpeeches(limit, page));
			response.setTotalHits(sd.getSpeechesTotalCount());
			
		}else {
			SearchResponse searchRespons =es.searchQuery(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, "*", limit, page);
			
			response.setTotalHits(searchRespons.getHits().getTotalHits());
			response.setRecords(SpeechConverter.convertToSpeeches(searchRespons));
		}
		return response;
	}

	@Override
	public ServiceResponse<Speech> getPlenarySessionSpeeches(int id, int limit, int page) {
		
		ServiceResponse<Speech> response = new ServiceResponse<>();
		
		if (!ElasticClient.getInstance().isConnectionStatus()){
			
			response.setRecords(sd.getPlenarySessionSpeeches(id, limit, page));
			response.setTotalHits(sd.getSessionSpeechesTotalCount(id));
			
		}else {
			String field = "sessionId";
			SearchResponse searchResponse = es.searchSpecificListSession(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE,field, id, limit, page);
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(SpeechConverter.convertToSpeeches(searchResponse));
		}
		return response;
	}
}

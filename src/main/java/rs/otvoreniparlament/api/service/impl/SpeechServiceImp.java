package rs.otvoreniparlament.api.service.impl;

import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.IndexName;
import rs.otvoreniparlament.api.index.IndexType;
import rs.otvoreniparlament.api.index.search.ElasticSpeechesSearchService;
import rs.otvoreniparlament.api.service.ServiceResponse;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.util.SpeechConverter;

public class SpeechServiceImp implements SpeechService {

	private SpeechDao sd;
	private ElasticSpeechesSearchService elasticSearch;
	
	public SpeechServiceImp() {
		sd = new SpeechDao();
		elasticSearch = new ElasticSpeechesSearchService();
	}

	@Override
	public ServiceResponse<Speech> getMemberSpeeches(int id, int limit, int page, String qtext, String from, String to) {
		ServiceResponse<Speech> response = new ServiceResponse<>();
		
		if (Settings.getInstance().config.getElasticConfig().isUsingElastic() && 
				ElasticClient.getInstance().isConnectionStatus()) {
			
			SearchResponse searchResponse = elasticSearch.searchMemberSpeeches(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, id, limit, page, qtext, from, to);
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(SpeechConverter.convertToSpeeches(searchResponse));
		}else {
			response.setRecords(sd.getMemberSpeeches(id, limit, page, qtext, from, to));
			response.setTotalHits(sd.getMemberSpeechesTotalCount(id, qtext, from, to));
		}
		return response;
	}

	@Override
	public Speech getSpeech(int id) {
		if (Settings.getInstance().config.getElasticConfig().isUsingElastic() && 
				ElasticClient.getInstance().isConnectionStatus()) {
			SearchResponse searchResponse = elasticSearch.searchSpecificID(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, "speechid", String.valueOf(id));
			
			if (searchResponse.getHits().getTotalHits() == 0) {
				return null;
			}
			
			return SpeechConverter.convertToSpeech(searchResponse.getHits().getAt(0));
		} else {
			return sd.getSpeech(id);
		}
	}

	@Override
	public ServiceResponse<Speech> getSpeeches(int limit, int page) {
		
		ServiceResponse<Speech> response = new ServiceResponse<>();
		
		if (Settings.getInstance().config.getElasticConfig().isUsingElastic() && 
				ElasticClient.getInstance().isConnectionStatus()) {
			
			SearchResponse searchRespons = elasticSearch.searchQuery(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, "*", limit, page);
			
			response.setTotalHits(searchRespons.getHits().getTotalHits());
			response.setRecords(SpeechConverter.convertToSpeeches(searchRespons));
		}else {
			response.setRecords(sd.getSpeeches(limit, page));
			response.setTotalHits(sd.getSpeechesTotalCount());
		}
		return response;
	}

	@Override
	public ServiceResponse<Speech> getPlenarySessionSpeeches(int id, int limit, int page) {
		
		ServiceResponse<Speech> response = new ServiceResponse<>();
		
		if (Settings.getInstance().config.getElasticConfig().isUsingElastic() && 
				ElasticClient.getInstance().isConnectionStatus()) {
			
			SearchResponse searchResponse = elasticSearch.searchPlenarySessionSpeeches(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, "sessionId", id, limit, page);
			
			response.setTotalHits(searchResponse.getHits().getTotalHits());
			response.setRecords(SpeechConverter.convertToSpeeches(searchResponse));
		}else {
			response.setRecords(sd.getPlenarySessionSpeeches(id, limit, page));
			response.setTotalHits(sd.getSessionSpeechesTotalCount(id));
		}
		return response;
	}
}

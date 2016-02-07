package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class SpeechServiceImp implements SpeechService {

	protected ElasticSearchService es = new ElasticSearchService();
	protected SpeechDao sd = new SpeechDao();

	@Override
	public List<Speech> getMemberSpeeches(int id, int limit, int page, String qtext, String from, String to) {
		if (ElasticClient.connectionStatus == false){
			return sd.getMemberSpeeches(id, limit, page, qtext, from, to);
		}else {
			es.searchQuery(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE , qtext);
			return null; //transform hits in list!
		}
	}

	@Override
	public Speech getSpeech(int id) {
		if (ElasticClient.connectionStatus== false){
			return sd.getSpeech(id);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , String.valueOf(id));
			return null; //transform hits in list!
		}
	}

	@Override
	public List<Speech> getSpeeches(int limit, int page) {
		if (ElasticClient.connectionStatus== false){
			return sd.getSpeeches(limit, page);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , "");
			return null; //transform hits in list!
		}
	}

	@Override
	public List<Speech> getPlenarySessionSpeeches(int id, int limit, int page) {
		if (ElasticClient.connectionStatus == false){
			return sd.getPlenarySessionSpeeches(id, limit, page);
		}else {
			es.searchQuery(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE , String.valueOf(id));
			return null; //transform hits in list!
		}
	}
}

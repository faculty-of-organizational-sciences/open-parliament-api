package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;

public class SpeechServiceImp implements SpeechService {

	protected SpeechDao sd = new SpeechDao();

	@Override
	public List<Speech> getMemberSpeeches(int id, int limit, int page, String qtext, String from, String to) {
		return sd.getMemberSpeeches(id, limit, page, qtext, from, to);
	}

	@Override
	public Speech getSpeech(int id) {
		return sd.getSpeech(id);
	}

	@Override
	public List<Speech> getSpeeches(int limit, int page) {
		return sd.getSpeeches(limit, page);
	}

	@Override
	public List<Speech> getPlenarySessionSpeeches(int id, int limit, int page) {
		return sd.getPlenarySessionSpeeches(id, limit, page);
	}
}

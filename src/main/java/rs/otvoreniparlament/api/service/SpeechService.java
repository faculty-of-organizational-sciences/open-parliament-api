package rs.otvoreniparlament.api.service;

import java.util.List;

import rs.otvoreniparlament.api.domain.Speech;

public interface SpeechService {
	
	public ServiceResponse<Speech> getMemberSpeeches(int id, int limit, int page, String qtext, String from, String to);
	
	public Speech getSpeech(int id);

	public ServiceResponse<Speech> getSpeeches(int limit, int page);

	public ServiceResponse<Speech> getPlenarySessionSpeeches(int id, int limit, int page);

}

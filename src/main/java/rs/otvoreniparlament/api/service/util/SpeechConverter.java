package rs.otvoreniparlament.api.service.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.formatters.DateFormatter;

public class SpeechConverter {
	
	private static final Logger logger = LogManager.getLogger(SpeechConverter.class);

	public static List<Speech> convertToSpeeches(SearchResponse speechData) {
		List<Speech> speeches = new LinkedList<>();
		
		for (SearchHit s : speechData.getHits()) {
			
			Speech speech = convertToSpeech(s);
			
			speeches.add(speech);
		}
		return speeches;
	}
	
	public static Speech convertToSpeech(SearchHit speechData) {
		Speech speech = new Speech();
		speech.setMember(new Member());
		
		Map<String, Object> source = speechData.getSource();
			speech.setId((Integer)source.get("speechid"));
			speech.setText((String)source.get("text"));
			speech.setSessionDate(DateFormatter.parseFullTimeDate(source.get("sessiondate").toString()));
			speech.getMember().setId((Integer)source.get("speech-member-id"));
			
			int id = (Integer)source.get("sessionId");
			
			PlenarySession ps = new PlenarySession();
			ps.setId(id);
			
			speech.setPlenarySession(ps);

		return speech;
	}
	
}

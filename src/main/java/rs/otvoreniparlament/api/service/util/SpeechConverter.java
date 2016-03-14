package rs.otvoreniparlament.api.service.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.util.formatters.DateFormatter;

public class SpeechConverter {
	
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
		if(source.get("speechid")!= null){
			speech.setId((Integer)source.get("speechid"));
		}
		if(source.get("text")!= null){
			speech.setText((String)source.get("text"));
		}
		if(source.get("speech-member-id")!= null){
			speech.getMember().setId((Integer)source.get("speech-member-id"));
		}
		if(source.get("sessiondate")!= null){
			speech.setSessionDate(DateFormatter.parseFullTimeDate(source.get("sessiondate").toString()));
		}
		if(source.get("speech-member-name")!= null){
			speech.getMember().setName((String) source.get("speech-member-name"));
		}
		if(source.get("speech-member-surname")!= null){
			speech.getMember().setLastName((String)source.get("speech-member-surname"));
		}
			int id = (Integer)source.get("sessionId");
			PlenarySession ps = new PlenarySession();
			ps.setId(id);
			
			speech.setPlenarySession(ps);

		return speech;
	}
	
}

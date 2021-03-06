package rs.otvoreniparlament.api.service.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.rest.util.formatters.DateFormatter;

public class PlenarySessionConverter {

	public static List<PlenarySession> convertToSession(SearchResponse sessionsData) {

		List<PlenarySession> sessions = new LinkedList<>();

		for (SearchHit s : sessionsData.getHits()) {

			PlenarySession ps = convertToPlenarySession(s);
			sessions.add(ps);
		}
		return sessions;
	}

	public static PlenarySession convertToPlenarySession(SearchHit sessionData) {

		PlenarySession ps = new PlenarySession();

		Map<String, Object> source = sessionData.getSource();

		if (source.get("id") != null || source.get("id") != "" )
			ps.setId((Integer) source.get("id"));
		
		if (source.get("agenda") != null || source.get("agenda") != "" )
			ps.setAgenda((String) source.get("agenda"));
		
		if (source.get("transcript") != null || source.get("transcript") != "" )
			ps.setTranscriptText((String) source.get("transcript"));
		
		if (source.get("date") != null || source.get("date") != "" )
			ps.setDate(DateFormatter.parseFullTimeDate(source.get("date").toString()));

		return ps;
	}
}

package rs.otvoreniparlament.api.service.util;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.formatters.DateFormatter;

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

		if (source.get("id") != null)
			ps.setId((int) source.get("id"));
		
		if (source.get("agenda") != null)
			ps.setAgenda((String) source.get("agenda"));
		
		if (source.get("transcript") != null)
			ps.setTranscriptText((String) source.get("transcript"));
		
		if (source.get("date") != null)
			ps.setDate((Date) DateFormatter.parseFullTimeDate(source.get("date").toString()));

		return ps;
	}
}

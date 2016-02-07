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

	public static List<PlenarySession> convertToSession (SearchResponse sessionDate){
		List<PlenarySession> sessions = new LinkedList<>();
		for (SearchHit s : sessionDate.getHits()) {
			Map<String, Object> source = s.getSource();
			
			PlenarySession ps = new PlenarySession();
			ps.setId((int) source.get("id"));
			ps.setAgenda((String) source.get("agenda"));
			ps.setTranscriptText((String) source.get("transcript"));
			ps.setDate((Date) DateFormatter.parseFullTimeDate(source.get("date").toString()));
			
			sessions.add(ps);
		}
		return sessions;
	}
}

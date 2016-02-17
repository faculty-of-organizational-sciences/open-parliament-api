package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.formatters.DateFormatter;
import rs.otvoreniparlament.api.uri.UriGenerator;

public class PlenarySessionJsonParser {
	static int countElasticSession = 0;

	public static JsonArray serializePlenarySessions(List<PlenarySession> plenarySessions) {
		JsonArray array = new JsonArray();

		if (plenarySessions != null && !plenarySessions.isEmpty()) {

			for (PlenarySession ps : plenarySessions) {
				JsonObject jsonParty = serializePlenarySession(ps);
				array.add(jsonParty);
			}
		}
		return array;
	}

	public static JsonObject serializePlenarySession(PlenarySession ps) {

		JsonObject plenarySession = new JsonObject();

		if (ps != null) {

			JsonObject meta = new JsonObject();
			meta.addProperty("href", UriGenerator.generate(ps, ps.getId()));

			plenarySession.add("meta", meta);

			plenarySession.addProperty("id", ps.getId());

			if (ps.getDate() != null)
				plenarySession.addProperty("date", DateFormatter.format(ps.getDate()));

			String[] agenda = null;

			if (ps.getAgenda() != null && !ps.getAgenda().isEmpty())
				agenda = ps.getAgenda().split("\\s\\(\\d\\d\\d\\d\\)\\s");

			JsonArray agendaArray = new JsonArray();

			for (String a : agenda) {
				agendaArray.add(new JsonPrimitive(a));
			}

			plenarySession.add("agenda", agendaArray);

			if (ps.getTranscriptText() != null && !ps.getTranscriptText().isEmpty())
				plenarySession.addProperty("transcriptText", ps.getTranscriptText().replaceAll("\\<.*?>", ""));
		} else {
			plenarySession.addProperty("error", "There is no plenary session with the given ID.");
		}
		plenarySession.addProperty("count", countElasticSession);
		return plenarySession;
	}

}

package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.formatters.DateFormatter;
import rs.otvoreniparlament.api.uri.UriGenerator;

public class SpeechJsonParser {

	static int countElasticSpeech = 0;
	public static JsonObject serializeSpeeches(List<Speech> speeches, long counter) {
		
		JsonObject json = new JsonObject();
		JsonArray array = new JsonArray();

		if (speeches != null && !speeches.isEmpty()) {

			for (Speech s : speeches) {
				JsonObject jsonSpeech = serializeSpeech(s);
				array.add(jsonSpeech);
			}
		}
		json.add("dataArray", array);
		json.addProperty("count",counter );
		return json;
	}

	public static JsonObject serializeSpeech(Speech s) {
		JsonObject jsonSpeech = new JsonObject();

		if (s != null) {

			JsonObject meta = new JsonObject();
			meta.addProperty("href", UriGenerator.generate(s, s.getId()));

			jsonSpeech.add("meta", meta);

			jsonSpeech.addProperty("id", s.getId());

			jsonSpeech.addProperty("text", s.getText().replaceAll("\\<.*?>", ""));

			jsonSpeech.addProperty("sessionDate", DateFormatter.format(s.getSessionDate()));

			if (s.getPlenarySession() != null && s.getPlenarySession().getAgenda() != null) {

				String[] agenda = s.getPlenarySession().getAgenda().split("\\s\\(\\d\\d\\d\\d\\)\\s");

				JsonArray agendaArray = new JsonArray();

				for (String a : agenda) {
					agendaArray.add(new JsonPrimitive(a));
				}

				jsonSpeech.add("sessionAgenda", agendaArray);
			}

			JsonObject creatorJson = new JsonObject();

			if (s.getMember() != null) {

				Member m = s.getMember();

				creatorJson.addProperty("firstName", m.getName());

				creatorJson.addProperty("lastName", m.getLastName());

				JsonObject creatorMeta = new JsonObject();
				creatorMeta.addProperty("href", UriGenerator.generate(m, m.getId()));

				creatorJson.add("meta", creatorMeta);

			} else {
				creatorJson.addProperty("error", "There is no Member with the given ID.");
			}
			jsonSpeech.add("creator", creatorJson);

			JsonObject plenarySession = new JsonObject();

			if (s.getPlenarySession() != null) {

				PlenarySession ps = s.getPlenarySession();

				JsonObject plenarySessionMeta = new JsonObject();

				plenarySessionMeta.addProperty("href", UriGenerator.generate(ps, ps.getId()));

				plenarySession.add("meta", plenarySessionMeta);

			} else {
				plenarySession.addProperty("error", "There is no plenary Session with the given ID.");
			}
			jsonSpeech.add("plenarySession", plenarySession);

		} else {
			jsonSpeech.addProperty("error", "There is no speech with the given ID.");
		}
		return jsonSpeech;
	}

}

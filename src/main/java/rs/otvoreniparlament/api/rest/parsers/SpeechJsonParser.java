package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.formatters.DateFormatter;
import rs.otvoreniparlament.api.uri.UriGenerator;

public class SpeechJsonParser {

	public static JsonArray serializeSpeeches(List<Speech> speeches) {
		JsonArray array = new JsonArray();

		if (speeches != null && !speeches.isEmpty()) {

			for (Speech s : speeches) {
				JsonObject jsonSpeech = serializeSpeech(s);
				array.add(jsonSpeech);
			}
		}
		return array;
	}

	public static JsonObject serializeSpeech(Speech s) {
		JsonObject jsonSpeech = new JsonObject();

		if (s != null) {

			JsonObject meta = new JsonObject();
			meta.addProperty("href", UriGenerator.generate(s, s.getId()));

			jsonSpeech.add("meta", meta);

			jsonSpeech.addProperty("id", s.getId());

			jsonSpeech.addProperty("text", s.getText().replaceAll("\\<.*?>",""));

			jsonSpeech.addProperty("sessionDate", DateFormatter.format(s.getSessionDate()));

			JsonObject plenarySession = new JsonObject();

			if (s.getPlenarySession() != null) {

				PlenarySession ps = s.getPlenarySession();
				
				JsonObject plenarySessionMeta = new JsonObject();
				
				plenarySessionMeta.addProperty("href", UriGenerator.generate(ps, ps.getId()));

				plenarySession.add("meta", meta);
				
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

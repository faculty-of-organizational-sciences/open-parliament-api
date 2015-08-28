package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
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

			jsonSpeech.addProperty("sessionDate", s.getSessionDate().toString());

			if (s.getPlenarySession() != null) {

				PlenarySession ps = s.getPlenarySession();
				
				JsonObject plenarySession = new JsonObject();
				
				plenarySession.addProperty("id", ps.getId());
				
				if (ps.getAgenda() != null && !ps.getAgenda().isEmpty())
				plenarySession.addProperty("agenda", ps.getAgenda());
				
				if (ps.getTranscriptText() != null && !ps.getTranscriptText().isEmpty())
				plenarySession.addProperty("transcriptText", ps.getTranscriptText().replaceAll("\\<.*?>",""));
				
				jsonSpeech.add("plenarySession", plenarySession);
			}

		} else {
			jsonSpeech.addProperty("error", "There is no speech with the given ID.");
		}
		return jsonSpeech;
	}

}

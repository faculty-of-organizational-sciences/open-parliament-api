package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.uri.UriGenerator;

public class PartyJsonParser {

	static int countElasticParty = 0;
	public static JsonArray serializeParties(List<Party> parties) {
		JsonArray array = new JsonArray();

		if (parties != null && !parties.isEmpty()) {

			for (Party p : parties) {
				JsonObject jsonParty = serializeParty(p);
				array.add(jsonParty);
			}
		}

		return array;
	}

	public static JsonObject serializeParty(Party p) {

		JsonObject jsonParty = new JsonObject();

		if (p != null) {
			
			JsonObject meta = new JsonObject();
			meta.addProperty("href", UriGenerator.generate(p, p.getId()));

			jsonParty.add("meta", meta);
			
			jsonParty.addProperty("id", p.getId());

			if (p.getName() != null && p.getName() != "") {
				jsonParty.addProperty("title", p.getName());
			}
			
			jsonParty.addProperty("members", UriGenerator.generate(p, p.getId()) + "/members");
		} else {
			jsonParty.addProperty("error", "There is no party with the given ID.");
		}
		jsonParty.addProperty("count", countElasticParty);
		return jsonParty;
	}
}

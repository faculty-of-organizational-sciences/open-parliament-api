package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.uri.UriGenerator;

public class PartyJsonParser {

	public static JsonArray serializeParties(List<Party> parties, boolean includeMembers) {
		JsonArray array = new JsonArray();

		if (parties != null && !parties.isEmpty()) {

			for (Party p : parties) {
				JsonObject jsonParty = serializeParty(p, includeMembers);
				array.add(jsonParty);
			}
		}

		return array;
	}

	public static JsonObject serializeParty(Party p, boolean includeMembers) {

		JsonObject jsonParty = new JsonObject();

		if (p != null) {
			
			JsonObject meta = new JsonObject();
			meta.addProperty("href", UriGenerator.generate(p, p.getId()));

			jsonParty.add("meta", meta);

			jsonParty.addProperty("id", p.getId());

			if (p.getName() != null && p.getName() != "") {
				jsonParty.addProperty("name", p.getName());
			}

			if (includeMembers == true && p.getMembers() != null && !p.getMembers().isEmpty()) {
				JsonArray members = new JsonArray();

				for (Member m : p.getMembers()) {
					JsonObject jsonMember = new JsonObject();

					if (m != null) {
						jsonMember.addProperty("id", m.getId());
						jsonMember.addProperty("name", m.getName());
						jsonMember.addProperty("lastName", m.getLastName());
						members.add(jsonMember);
					}
				}
				jsonParty.add("members", members);
			}
		} else {
			jsonParty.addProperty("error", "There is no party with the given ID.");
		}
		return jsonParty;
	}
}

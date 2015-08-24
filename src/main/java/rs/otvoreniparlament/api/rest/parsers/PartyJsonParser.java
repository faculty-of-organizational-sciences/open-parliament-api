package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public class PartyJsonParser {

	private static Gson gson;

	static {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public static String serializeParties(List<Party> parties, boolean includeMembers) {
		JsonArray array = new JsonArray();

		if (parties != null && !parties.isEmpty()) {

			for (Party p : parties) {
				JsonObject jsonParty = serializePartyJson(p, includeMembers);
				array.add(jsonParty);
			}
		}

		String json = gson.toJson(array);
		System.out.println(json);
		return json;
	}

	public static String serializeParty(Party p, boolean includeMembers) {

		JsonObject jsonParty = new JsonObject();

		if (p != null) {

			jsonParty.addProperty("id", p.getPartyId());

			if (p.getName() != null && p.getName() != "") {
				jsonParty.addProperty("name", p.getName());
			}

			if (includeMembers == true && p.getMembers() != null && !p.getMembers().isEmpty()) {
				JsonArray members = new JsonArray();

				for (Member m : p.getMembers()) {
					JsonObject jsonMember = new JsonObject();

					if (m != null) {
						jsonMember.addProperty("id", m.getMemberID());
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
		String json = gson.toJson(jsonParty);
		System.out.println(json);
		return json;
	}

	public static JsonObject serializePartyJson(Party p, boolean includeMembers) {
		JsonObject json = gson.fromJson(serializeParty(p, includeMembers), JsonObject.class);
		return json;
	}
}

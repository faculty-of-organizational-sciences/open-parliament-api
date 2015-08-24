package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public class MemberJsonParser {

	private static Gson gson;

	static {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public static String serializeMembers(List<Member> members) {
		JsonArray array = new JsonArray();

		if (members != null && !members.isEmpty()) {

			for (Member m : members) {
				JsonObject jsonMember = MemberJsonParser.serializeMemberJson(m);
				array.add(jsonMember);
			}
		}

		String json = gson.toJson(array);
		System.out.println(json);
		return json;
	}

	public static String serializeMember(Member m) {

		JsonObject jsonMember = new JsonObject();

		if (m != null) {

			jsonMember.addProperty("id", m.getMemberID());
			jsonMember.addProperty("name", m.getName());
			jsonMember.addProperty("lastName", m.getLastName());

			if (m.getEmail() != null) {
				jsonMember.addProperty("mail", m.getEmail());
			}

			if (m.getDateOfBirth() != null) {
				jsonMember.addProperty("birthDate", m.getDateOfBirth().toString());
			}

			if (m.getPlaceOfBirth() != null) {
				jsonMember.addProperty("birthPlace", m.getPlaceOfBirth().getName());
			}

			if (m.getPlaceOfResidence() != null) {
				jsonMember.addProperty("placeOfResidence", m.getPlaceOfResidence().getName());
			}

			if (m.getGender() != null) {
				jsonMember.addProperty("gender", m.getGender());
			}

			if (m.getBiography() != null && m.getBiography() != "") {
				jsonMember.addProperty("biography", m.getBiography());
			}

			if (m.getParties() != null && !m.getParties().isEmpty()) {
				JsonArray parties = new JsonArray();

				for (Party p : m.getParties()) {
					JsonObject jsonParty = new JsonObject();

					if (p != null && p.getName() != "") {
						jsonParty.addProperty("partyId", p.getPartyId());
						jsonParty.addProperty("partyName", p.getName());
						parties.add(jsonParty);
					}
				}
				jsonMember.add("parties", parties);
			}
		} else {
			jsonMember.addProperty("error", "There is no member with the given ID.");
		}

		String stringJson = gson.toJson(jsonMember);
		System.out.println(stringJson);
		return stringJson;
	}

	public static JsonObject serializeMemberJson(Member m) {
		JsonObject json = gson.fromJson(serializeMember(m), JsonObject.class);
		return json;
	}
}

package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.formatters.DateFormatter;
import rs.otvoreniparlament.api.formatters.GenderFormatter;
import rs.otvoreniparlament.api.uri.UriGenerator;

public class MemberJsonParser {

	public static JsonObject serializeMembers(List<Member> members, long count) {
		JsonObject json = new JsonObject();
		JsonArray array = new JsonArray();
		
		if (members != null && !members.isEmpty()) {

			for (Member m : members) {
				JsonObject jsonMember = serializeMember(m);
				array.add(jsonMember);
			}
		} 
		json.add("dataArray", array);
		json.addProperty("count", count);
		return json;
	}

	public static JsonObject serializeMember(Member m) {

		 JsonObject jsonMember = new JsonObject();

		if (m != null) {

			JsonObject meta = new JsonObject();
			meta.addProperty("href", UriGenerator.generate(m, m.getId()));

			jsonMember.add("meta", meta);
			jsonMember.addProperty("id", m.getId());
			jsonMember.addProperty("name", m.getName());
			jsonMember.addProperty("lastName", m.getLastName());

			if (m.getEmail() != null) {
				jsonMember.addProperty("mail", m.getEmail());
			}

			if (m.getDateOfBirth() != null) {
				jsonMember.addProperty("birthDate", DateFormatter.format(m.getDateOfBirth()));
			}

			if (m.getPlaceOfBirth() != null) {
				jsonMember.addProperty("birthPlace", m.getPlaceOfBirth().getName());
			}

			if (m.getPlaceOfResidence() != null) {
				jsonMember.addProperty("placeOfResidence", m.getPlaceOfResidence().getName());
			}

			if (m.getGender() != null) {
				jsonMember.addProperty("gender", GenderFormatter.format(m.getGender()));
			}

			if (m.getBiography() != null && m.getBiography() != "") {
				jsonMember.addProperty("biography", m.getBiography().replaceAll("\\<.*?>", ""));
			}

			if (m.getParties() != null && !m.getParties().isEmpty()) {
				JsonArray parties = new JsonArray();

				for (Party p : m.getParties()) {
					parties.add(PartyJsonParser.serializeParty(p));
				}
				jsonMember.add("parties", parties);
			}
		
		}
		return jsonMember;
	}

}

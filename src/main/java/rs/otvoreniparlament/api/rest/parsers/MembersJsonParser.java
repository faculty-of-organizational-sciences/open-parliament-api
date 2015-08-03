package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import rs.otvoreniparlament.api.domain.Member;
<<<<<<< HEAD
import rs.otvoreniparlament.api.domain.Party;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MembersJsonParser {

	private static Gson gson;

	static {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public static String serializeMembers(List<Member> members) {
		JsonArray array = new JsonArray();

		if (members != null && !members.isEmpty()) {

			for (Member m : members) {
				JsonObject jsonMember = new JsonObject();

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

				array.add(jsonMember);
			}
		}

		String json = gson.toJson(array);
		System.out.println(json);
		return json;
=======
import rs.otvoreniparlament.api.uri.UriGenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MembersJsonParser {

	private static Gson gson;

	static {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public static String serializeMembers(List<Member> members) {
		JsonArray array = new JsonArray();

		if (members != null && !members.isEmpty()) {

			for (Member m : members) {
				JsonObject jsonMember = new JsonObject();

				Integer memberId = m.getMemberID();
				UriGenerator uri = new UriGenerator(m, memberId);

				jsonMember.addProperty("name", m.getName());
				jsonMember.addProperty("lastName", m.getLastName());

				array.add(jsonMember);
			}
		}

		return gson.toJson(array);
>>>>>>> refs/remotes/origin/master
	}
}

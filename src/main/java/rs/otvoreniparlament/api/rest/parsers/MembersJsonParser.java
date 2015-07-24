package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import rs.otvoreniparlament.api.domain.Member;
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
	}
}

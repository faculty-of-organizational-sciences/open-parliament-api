package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Member;

public class MembersJsonParser {

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
}

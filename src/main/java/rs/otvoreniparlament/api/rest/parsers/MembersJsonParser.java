package rs.otvoreniparlament.api.rest.parsers;

import java.util.List;

import rs.otvoreniparlament.api.domain.Poslanik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MembersJsonParser {
	
	private static Gson gson;
	
	static {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public static String serializeMembers(List<Poslanik> members) {
		JsonArray array = new JsonArray();
		
		if (members != null && !members.isEmpty()) {
		
			for (Poslanik p : members) {
				JsonObject jsonMember = new JsonObject();
				
				jsonMember.addProperty("name", p.getImePoslanika());
				jsonMember.addProperty("lastName", p.getPrezimePoslanika());
				
				array.add(jsonMember);
			}
		}
		
		return gson.toJson(array);
	}
}

package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.gson.JsonObject;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.rest.parsers.PartyJsonParser;

@RunWith(Parameterized.class)
public class PartyJsonParserTest {

	private JsonObject data;
	private JsonObject test;

	public PartyJsonParserTest(JsonObject data, JsonObject test) {
		super();
		this.data = data;
		this.test = test;
	}

	@Parameters
	public static Collection<Object[]> data() {

		List<Member> members = new LinkedList<Member>();

		Party p1 = new Party();
		p1.setId(1);
		p1.setName("ime Partije");
		p1.setMembers(members);

		JsonObject partyJson = new JsonObject();

		JsonObject meta = new JsonObject();
		meta.addProperty("href", "http://localhost:9090/api/parties/1");

		partyJson.add("meta", meta);
		partyJson.addProperty("id", 1);
		partyJson.addProperty("title", "ime Partije");
		partyJson.addProperty("members", "http://localhost:9090/api/parties/1/members");

		return Arrays.asList(new Object[][] { { partyJson, PartyJsonParser.serializeParty(p1) } });
	}

	@Test
	public void testSerializeParties() {

	}

	@Test
	public void testSerializeParty() {
		assertEquals(test, data);
	}

}

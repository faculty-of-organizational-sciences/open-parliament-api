package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.Town;
import rs.otvoreniparlament.api.rest.parsers.json.MemberJsonParser;
import rs.otvoreniparlament.api.rest.util.formatters.DateFormatter;

import com.google.gson.JsonObject;

@RunWith(Parameterized.class)
public class MemberJsonParserTest {

	private JsonObject data;
	private JsonObject test;

	public MemberJsonParserTest(JsonObject data, JsonObject test) {
		super();
		this.data = data;
		this.test = test;
	}
	@Parameters
	public static Collection<Object[]> data() {

		List<Party> parties = new LinkedList<Party>();
		Town vranje = new Town();
		vranje.setName("vranje");
		Town bg = new Town();
		bg.setName("bg");
		
		Member m1 = new Member();
		m1.setId(123);
		m1.setName("Pera");
		m1.setLastName("Peric");
		Date d = new Date(245721600000L);
		System.out.println(DateFormatter.format(d));
		m1.setDateOfBirth(d);
		m1.setGender("0");
		m1.setEmail("p.peric@gmail.com");
		m1.setBiography("Rodjen u Vranju...");
		m1.setPlaceOfBirth(vranje);
		m1.setPlaceOfResidence(bg);
		m1.setParties(parties);

		JsonObject memberJson = new JsonObject();

		JsonObject meta = new JsonObject();
		meta.addProperty("href", "http://localhost:9090/members/123");

		memberJson.add("meta", meta);
		memberJson.addProperty("id", 123);
		memberJson.addProperty("name", "Pera");
		memberJson.addProperty("lastName", "Peric");
		memberJson.addProperty("mail", "p.peric@gmail.com");
		memberJson.addProperty("birthDate", "15.10.1977.");
		memberJson.addProperty("birthPlace", "vranje");
		memberJson.addProperty("placeOfResidence", "bg");
		memberJson.addProperty("gender", "m");
		memberJson.addProperty("biography", "Rodjen u Vranju...");

		return Arrays.asList(new Object[][] { { memberJson, MemberJsonParser.serializeMember(m1) } });
	}
	@Test
	public void testSerializeMembers() {

	}

	@Test
	public void testSerializeMember() {
		assertEquals(test, data);
	}
}

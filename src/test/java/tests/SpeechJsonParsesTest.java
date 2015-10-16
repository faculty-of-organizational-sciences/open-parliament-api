package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.parsers.SpeechJsonParser;

import com.google.gson.JsonObject;

@RunWith(Parameterized.class)
public class SpeechJsonParsesTest {
	private JsonObject data;
	private JsonObject test;
	
	public SpeechJsonParsesTest(JsonObject data, JsonObject test){
		super();
		this.data = data;
		this.test = test;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		
		Speech s1 = new Speech();
		s1.setId(123);
		s1.setText("tekst obracanja");
		Member m1 = new Member();
		m1.setId(2);
		m1.setName("ime");
		m1.setLastName("prezime");
		s1.setMember(m1);
		PlenarySession ps1 = new PlenarySession();
		ps1.setId(1);
		s1.setPlenarySession(ps1);
		Date d = new Date(1384300800000L);
		s1.setSessionDate(d);
		
		JsonObject speechJson = new JsonObject();

		JsonObject meta = new JsonObject();
		meta.addProperty("href", "http://localhost:9090/speeches/123");
		
		speechJson.add("meta", meta);
		speechJson.addProperty("id", 123);
		speechJson.addProperty("text", "tekst obracanja");
		speechJson.addProperty("sessionDate", "13.11.2013.");
		
		JsonObject memberJson = new JsonObject();
		memberJson.addProperty("firstName", "ime");
		memberJson.addProperty("lastName", "prezime");
		JsonObject meta1 = new JsonObject();
		meta1.addProperty("href", "http://localhost:9090/members/2");
		memberJson.add("meta", meta1);
		
		speechJson.add("creator", memberJson);
		
		JsonObject plenarySessionJson = new JsonObject();
		JsonObject meta2 = new JsonObject();
		meta2.addProperty("href", "http://localhost:9090/sessions/1");
		plenarySessionJson.add("meta", meta2);
		speechJson.add("plenarySession", plenarySessionJson);
		
		return Arrays.asList(new Object[][] { { speechJson, SpeechJsonParser.serializeSpeech(s1) } });
	}
	@Test
	public void testSerializeSpeeches() {

	}

	@Test
	public void testSerializeSpeech() {
		assertEquals(test, data);
	}
}

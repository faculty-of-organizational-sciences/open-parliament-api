package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.rest.parsers.json.PlenarySessionJsonParser;

import com.google.gson.JsonObject;

@RunWith(Parameterized.class)
public class PlenarySessionJsonParserTest {

	private JsonObject data;
	private JsonObject test;
	
	public PlenarySessionJsonParserTest(JsonObject data, JsonObject test){
		super();
		this.data = data;
		this.test = test;
	}
	@Parameters
	public static Collection<Object[]> data(){
		PlenarySession ps1 = new PlenarySession();
		ps1.setId(123);
		ps1.setAgenda("tekst dnevnog reda");
		ps1.setTranscriptText("tekst transkripta");
		Date d = new Date(1413417600000L);
		ps1.setDate(d);
		
		JsonObject plenarySessionJson = new JsonObject();
		JsonObject meta = new JsonObject();
		meta.addProperty("href", "http://localhost:9090/sessions/123");
		
		plenarySessionJson.add("meta", meta);
		plenarySessionJson.addProperty("id", 123);
		plenarySessionJson.addProperty("date", "16.10.2014.");
		plenarySessionJson.addProperty("agenda", "tekst dnevnog reda");
		plenarySessionJson.addProperty("transcriptText", "tekst transkripta");
		
		return Arrays.asList(new Object[][] { { plenarySessionJson, PlenarySessionJsonParser.serializePlenarySession(ps1) } });
	}
	@Test
	public void testSerializePlenarySessions() {

	}

	@Test
	public void testSerializePlenarySession() {
		assertEquals(test, data);
	}
}

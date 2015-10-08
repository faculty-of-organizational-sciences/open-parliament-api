package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.Assembly;
import rs.otvoreniparlament.api.domain.Country;
import rs.otvoreniparlament.api.domain.FriendshipGroup;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.PartyList;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Region;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.domain.Town;
import rs.otvoreniparlament.api.uri.UriGenerator;
import rs.otvoreniparlament.api.uri.exception.MissingRestServiceNameAnnotationException;

public class UriGeneratorTest {
	
	String prefix = Settings.getInstance().config.uriGenerator.uriPrefix;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void testFriendshipGroupUri() {
		expected.expect(MissingRestServiceNameAnnotationException.class);
		UriGenerator.generate(new FriendshipGroup(), 155);
	}

	@Test
	public void testAssemblyUri() {
		expected.expect(MissingRestServiceNameAnnotationException.class);
		UriGenerator.generate(new Assembly(), 155);
	}

	@Test
	public void testCountryUri() {
		expected.expect(MissingRestServiceNameAnnotationException.class);
		UriGenerator.generate(new Country(), 155);
	}
	

	@Test
	public void testPartyListUri() {
		expected.expect(MissingRestServiceNameAnnotationException.class);
		UriGenerator.generate(new PartyList(), 155);
	}
	
	@Test
	public void testRegionUri() {
		expected.expect(MissingRestServiceNameAnnotationException.class);
		UriGenerator.generate(new Region(), 155);
	}
	
	@Test
	public void testTownUri() {
		expected.expect(MissingRestServiceNameAnnotationException.class);
		UriGenerator.generate(new Town(), 155);
	}
	
	@Test
	public void testMemberUri(){
		assertEquals(UriGenerator.generate(new Member(), 155), prefix + "members/155");
	}
	
	@Test
	public void testPartyUri(){
		assertEquals(UriGenerator.generate(new Party(), 155), prefix + "parties/155");
	}
	
	@Test
	public void testSessionUri(){
		assertEquals(UriGenerator.generate(new PlenarySession(), 155), prefix + "sessions/155");
	}
	
	@Test
	public void testSpeechUri(){
		assertEquals(UriGenerator.generate(new Speech(), 155), prefix + "speeches/155");
	}
}

package tests;

import static org.junit.Assert.assertEquals;



import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.domain.meta.RestServiceName;
import rs.otvoreniparlament.api.uri.UriGenerator;



@RunWith(Parameterized.class)
public class UriGeneratorTest {

	private String result;
	static Object o;
	static Member m;
	static PlenarySession ps;
	static Speech s;
	static Party p;
	

	public UriGeneratorTest(Object o, String result) {
		super();
		this.o= o;
		this.result = result;
	}
	 @Before public void initialize() {
	       m = new Member();
	       ps = new PlenarySession();
	       p  = new Party();
	       s = new Speech();
	    }
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { {null, UriGenerator.generate(m, 432) },
												{"http://api.otvoreniparlament.rs/v1/members/179", UriGenerator.generate(m, 179) },
													{null, UriGenerator.generate(ps, 990) },
														{"http://api.otvoreniparlament.rs/v1/sessions/889", UriGenerator.generate(ps, 889)},
																{null, UriGenerator.generate(s, 30000) },
																	{"http://api.otvoreniparlament.rs/v1/speeches/179", UriGenerator.generate(s, 28808) },
																		{null, UriGenerator.generate(p, 2000) },
																			{"http://api.otvoreniparlament.rs/v1/parties/420", UriGenerator.generate(p, 420)}});
	}
	@Test
	public void testFormat() {
		assertEquals(o, result);
	}
}

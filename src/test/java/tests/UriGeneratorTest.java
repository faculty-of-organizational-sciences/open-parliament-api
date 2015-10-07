package tests;

import static org.junit.Assert.assertEquals;









import java.util.Arrays;
import java.util.Collection;

import javax.swing.plaf.synth.Region;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import rs.otvoreniparlament.api.domain.Assembly;
import rs.otvoreniparlament.api.domain.Country;
import rs.otvoreniparlament.api.domain.FriendshipGroup;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.PartyList;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.domain.Town;
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
	static Assembly a;
	static Country c;
	static Town t;
	static FriendshipGroup fg;
	static PartyList pl;
	static rs.otvoreniparlament.api.domain.Region r;
	

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
	       a = new Assembly();
	       c = new Country();
	       t = new Town();
	       fg = new FriendshipGroup();
	       pl = new PartyList();
	       r = new rs.otvoreniparlament.api.domain.Region();
	    }
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { {"Passed object's class does not have declared RestServiceName annotation", UriGenerator.generate(fg, 895) },
												{"Passed object's class does not have declared RestServiceName annotation", UriGenerator.generate(pl, 895) },
													{"Passed object's class does not have declared RestServiceName annotation", UriGenerator.generate(r, 895) },
														{"Passed object's class does not have declared RestServiceName annotation", UriGenerator.generate(a, 895) },
															{"Passed object's class does not have declared RestServiceName annotation", UriGenerator.generate(c, 895) },
																{"Passed object's class does not have declared RestServiceName annotation", UriGenerator.generate(t, 895) },
																	{"http://api.otvoreniparlament.rs/v1/members/179", UriGenerator.generate(m, 179) },
																		{"http://api.otvoreniparlament.rs/v1/sessions/889", UriGenerator.generate(ps, 889)},
																			{"http://api.otvoreniparlament.rs/v1/speeches/179", UriGenerator.generate(s, 28808) },
																				{"http://api.otvoreniparlament.rs/v1/parties/420", UriGenerator.generate(p, 420)}});
	}
	@Test
	public void testFormat() {
		assertEquals(o, result);
	}
}

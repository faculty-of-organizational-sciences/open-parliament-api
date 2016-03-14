package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import rs.otvoreniparlament.api.rest.util.formatters.GenderFormatter;

@RunWith(Parameterized.class)
public class GenderFormatterTest {

	private String gender;
	private String result;

	public GenderFormatterTest(String gender, String result) {
		super();
		this.gender = gender;
		this.result = result;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "m", GenderFormatter.format("0") },
												{ "f", GenderFormatter.format("1")},
													{ null, GenderFormatter.format("sadsa")} });
	}

	@Test
	public void testFormat() {
		assertEquals(gender, result);
	}

}

package rs.otvoreniparlament.api.rest.util.formatters;

public class GenderFormatter {

	public static String format(String g) {
		switch (g) {
		case "0":
			return "m";
		case "1":
			return "f";
		default:
			return null;
		}
	}

}

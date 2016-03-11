package rs.otvoreniparlament.api.formatters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateRegex {

	String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
	private static Pattern pattern;
	private static Matcher matcher;

	public DateRegex() {
		pattern = Pattern.compile(regex);
	}

	public static boolean ValidateDate(String date) {
		matcher = pattern.matcher(date);

		if (matcher.matches()) {
			matcher.reset();
			if (matcher.find()) {
				String day = matcher.group(3);
				String month = matcher.group(2);
				int year = Integer.parseInt(matcher.group(1));
				if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9")
						|| month.equals("11") || month.equals("04") || month.equals("06") || month.equals("09"))) {
					return false; // only 1,3,5,7,8,10,12 has 31 days
				} else if (month.equals("2") || month.equals("02")) {// leap year
					if (year % 4 == 0) {
						if (day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					} else {
						if (day.equals("29") || day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		}else
		return false;
	}
}

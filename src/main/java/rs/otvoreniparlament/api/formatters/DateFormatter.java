package rs.otvoreniparlament.api.formatters;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateFormatter {

	public static String format(Date d) {
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		
		return dateFormat.format(d);
	}
}

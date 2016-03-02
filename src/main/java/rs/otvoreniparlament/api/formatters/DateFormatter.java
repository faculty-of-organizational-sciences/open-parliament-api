package rs.otvoreniparlament.api.formatters;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateFormatter {

	private static final Logger logger = LogManager.getLogger(DateFormatter.class);
	private static final String FULL_TIME_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final String DATE_DOT_FORMAT = "dd.MM.yyyy.";

	public static String format(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_DOT_FORMAT);

		return dateFormat.format(d);
	}

	public static Date parseFullTimeDate(String stringDate) {
		if(stringDate == null){
			logger.debug("stringDate is null");
			return null;
		}
		if(stringDate == ""){
			logger.debug("stringDate is empty");
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(FULL_TIME_DATE_FORMAT);
		Date utilDate;
		
		try {
			utilDate = dateFormat.parse(stringDate);
		} catch (ParseException e1) {
			logger.error(e1);
			return null;
		}
		return utilDate;
	}
}

package app05b.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DataFormatter implements Formatter<Date> {
	private String datePattern;
	private SimpleDateFormat dateFormat;

	public DataFormatter(String datePattern) {
		this.datePattern = datePattern;
		dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
	}

	@Override
	public String print(Date date, Locale locale) {
		System.out.println("call DateFormatter==>print()");
		return dateFormat.format(date);
	}

	@Override
	public Date parse(String s, Locale locale) throws ParseException {
		try {
			System.out.println("call DateFormatter==>parse()");
			return dateFormat.parse(s);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"invalid date format. Please use this pattern \""
							+ datePattern + "\"");
		}
	}

}

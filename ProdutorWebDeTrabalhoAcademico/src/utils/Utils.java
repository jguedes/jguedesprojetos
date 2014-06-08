package utils;

import java.text.DateFormat;
import java.util.Date;

public class Utils {
	/**
	 * @param date
	 * @return A uma String com a data e hora no formato short padrão do
	 *         sistema.
	 */
	public static String data(long date) {
		return DateFormat.getInstance().format(new Date(date));
	}
}

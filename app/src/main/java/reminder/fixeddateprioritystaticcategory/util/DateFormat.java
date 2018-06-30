package reminder.fixeddateprioritystaticcategory.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/*** added by dManageReminder
 */
public class DateFormat {
	public static Date dateFormater(String dateUnformated) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm",
			Locale.getDefault());
		return sdf.parse(dateUnformated);
	}
}
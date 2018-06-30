package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.cp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.DBException;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Reminder;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db.DBConstants;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db.DefaultDBFactory;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db.ReminderDAO;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Category;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db.CategoryDAO;
/*** added by dManageReminder* modified by dStaticCategory
 */
public class ReminderContentProvider extends ContentProvider {
	private static final int REMINDERS = 10;
	private static final String SECURITY_EXCEPTION =
	"You are not allowed to call this method";
	private static final String AUTHORITY =
	"br.com.positivo.reminders.contentprovider";
	private static final String BASE_PATH = "reminders";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"
		+ BASE_PATH);
	public static final String text() {
		return DBConstants.REMINDER_TEXT_COLUMN;
	}
	public static final String date() {
		return DBConstants.REMINDER_DATE_COLUMN;
	}
	public static final String hour() {
		return DBConstants.REMINDER_HOUR_COLUMN;
	}
	private ReminderDAO rdao;
	private static final UriMatcher sURIMatcher = new
	UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, REMINDERS);
	}
	/*** modified by dStaticCategory
	 */
	@Override
	public boolean onCreate() {
		cdao = DefaultDBFactory.factory(getContext()).createCategoryDAO();
		rdao = DefaultDBFactory.factory(getContext()).createReminderDAO();
		return false;
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		try {
			Reminder reminder = createReminderInsert(values);
			reminder.setText(values.getAsString(text()));
			Long id = rdao.saveReminder(reminder);
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(BASE_PATH + "/" + id);
		}
		catch(DBException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	/*** modified by dStaticCategory
	 */
	private Reminder createReminderInsert(ContentValues values) throws DBException
	{
		Reminder reminder = createReminderInsert_original0(values);
		Category category = createCategoryInsert(values);
		reminder.setCategory(category);
		return reminder;
	}
	@Override
	public Cursor query(Uri arg0, String [] arg1, String arg2, String [] arg3,
		String arg4) {
		return null;
	}
	@Override
	public int delete(Uri arg0, String arg1, String [] arg2) {
		throw new SecurityException(SECURITY_EXCEPTION);
	}
	@Override
	public String getType(Uri arg0) {
		return null;
	}
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String [] arg3) {
		throw new SecurityException(SECURITY_EXCEPTION);
	}
	/*** added by dStaticCategory
	 */
	public static final String category() {
		return DBConstants.CATEGORY_NAME_COLUMN;
	}
	/*** added by dStaticCategory
	 */
	private CategoryDAO cdao;
	/*** added by dStaticCategory
	 */
	private Category createCategoryInsert(ContentValues values) throws DBException
	{
		Category category = cdao.findCategory(values.getAsString(category()));
		return category;
	}
	/*** modified by dStaticCategory
	 */
	private Reminder createReminderInsert_original0(ContentValues values) throws
	DBException {
		Reminder reminder = new Reminder();
		reminder.setDate(values.getAsString(date()));
		reminder.setHour(values.getAsString(hour()));
		return reminder;
	}
}
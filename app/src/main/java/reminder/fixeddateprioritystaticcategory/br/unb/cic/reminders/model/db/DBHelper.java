package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/*** added by dManageReminder* modified by dStaticCategory
 */
public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context, String name, CursorFactory factory, int
		version) {
		super(context, name, factory, version);
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		db.execSQL("PRAGMA foreign_keys=ON;");
		super.onOpen(db);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			for(String sql : DBConstants.DROP_TABLE_STATEMENTS) {
				db.execSQL(sql);
			}
			onCreate(db);
		}
		catch(SQLiteException e) {
			Log.v(DBHelper.class.getName(), e.getMessage());
		}
	}
	/*** added by dStaticCategory
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			for(String sql : DBConstants.CREATE_TABLE_STATEMENTS) {
				Log.i(DBHelper.class.getCanonicalName(), sql);
				db.execSQL(sql);
			}
			for(String sql : DBConstants.PREDEFINED_CATEGORIES) {
				Log.i(DBHelper.class.getCanonicalName(), sql);
				db.execSQL(sql);
			}
		}
		catch(SQLiteException e) {
			Log.v(DBHelper.class.getName(), e.getMessage());
		}
	}
}
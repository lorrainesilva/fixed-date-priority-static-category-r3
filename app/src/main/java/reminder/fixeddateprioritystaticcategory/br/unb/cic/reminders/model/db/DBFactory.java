package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db;

import android.content.Context;
/*** added by dManageReminder* modified by dStaticCategory
 */
public abstract class DBFactory {
	protected Context context;
	public static DBFactory factory(Context context) {
		return new DefaultDBFactory(context);
	}
	public DBFactory(Context context) {
		this.context = context;
	}
	public abstract ReminderDAO createReminderDAO();
	/*** added by dStaticCategory
	 */
	public abstract CategoryDAO createCategoryDAO();
}
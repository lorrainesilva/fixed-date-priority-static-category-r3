package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.db;

import android.content.Context;
/*** added by dManageReminder* modified by dStaticCategory
 */
public class DefaultDBFactory extends DBFactory {
	public DefaultDBFactory(Context context) {
		super(context);
	}
	@Override
	public ReminderDAO createReminderDAO() {
		return new DefaultReminderDAO(context);
	}
	/*** added by dStaticCategory
	 */
	@Override
	public CategoryDAO createCategoryDAO() {
		return new DefaultCategoryDAO(context);
	}
}
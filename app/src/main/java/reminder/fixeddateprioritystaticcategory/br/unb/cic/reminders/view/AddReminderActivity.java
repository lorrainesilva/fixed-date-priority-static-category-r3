package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view;

import java.util.List;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.DBException;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.controller.Controller;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Reminder;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dStaticCategory
 */
public class AddReminderActivity extends ReminderActivity {
	@Override
	protected void initializeValues() {
	}
	/*** added by dStaticCategory
	 */
	@Override
	protected void persist(Reminder reminder) {
		try {
			Category category = findCategory(reminder.getCategory());
			reminder.setCategory(category);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			Controller.instance(getApplicationContext()).updateReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
	/*** added by dStaticCategory
	 */
	private Category findCategory(Category category) throws Exception {
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		for(Category c : categories) {
			if(c.getName().equals(category.getName())) return c;
		}
		return null;
	}
}
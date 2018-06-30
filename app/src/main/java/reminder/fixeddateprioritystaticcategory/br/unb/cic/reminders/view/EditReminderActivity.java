package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.DBException;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.controller.Controller;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Reminder;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Priority;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dPriority* modified by
dStaticCategory
 */
public class EditReminderActivity extends ReminderActivity {
	@Override
	protected void initializeValues() {
		Intent intent = getIntent();
		long reminderId = intent.getLongExtra("id", 0);
		String text = intent.getStringExtra("text");
		String details = intent.getStringExtra("details");
		reminder.setId(reminderId);
		edtReminder.setText(text);
		edtDetails.setText(details);
		try {
			initializeValues(intent);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void initializeValues(Intent intent) throws Exception {
		initializeValues_original2(intent);
		String categoryName = intent.getStringExtra("category_name");
		String categoryId = intent.getStringExtra("category_id");
		Category category = new Category();
		category.setId(Long.parseLong(categoryId));
		category.setName(categoryName);
		spinnerCategory.setSelection(categoryToIndex(category));
	}
	/*** modified by dPriority
	 */
	private void initializeValues_original0(Intent intent) throws Exception {
		String date = intent.getStringExtra("date");
		String hour = intent.getStringExtra("hour");
		updateSpinnerDateHour(spinnerDate, date);
		updateDateFromString(date);
		updateSpinnerDateHour(spinnerTime, hour);
		updateTimeFromString(hour);
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void initializeValues_original2(Intent intent) throws Exception {
		initializeValues_original0(intent);
		String priority = intent.getStringExtra("priority");
		spinnerPriority.setSelection(Priority.fromCode(Integer.parseInt(priority,
					10)).getCode());
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
	private int categoryToIndex(Category category) throws Exception {
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		int i = 0;
		for(Category c : categories) {
			if(c.getName().equals(category.getName())) {
				return i;
			}
			i ++;
		}
		return 0;
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
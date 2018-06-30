package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
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
public class ExternalAddReminderActivity extends ReminderActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		reminder = new Reminder();
		try {
			setReminderFromIntent();
		}
		catch(Exception e) {
			Intent intent2 = new Intent(getApplicationContext(),
				AddReminderActivity.class);
			startActivity(intent2);
			finish();
		}
		super.onCreate(savedInstanceState);
	}
	private void setReminderFromIntent() throws Exception {
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if(action.equals("br.com.positivo.reminders.ADD_REMINDER") &&
			"text/plain".equals(type)) {
			String text = intent.getStringExtra("text");
			String details = intent.getStringExtra("details");
			reminder.setText(text);
			reminder.setDetails(details);
			reminderFromIntent(intent);
		}
		else reminder = null;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void reminderFromIntent(Intent intent) throws Exception {
		reminderFromIntent_original4(intent);
		setNewCategory(intent);
		reminder.setCategory(newCategory);
	}
	@Override
	protected void initializeValues() {
		if(! reminder.isValid()) return;
		edtReminder.setText(reminder.getText());
		edtDetails.setText(reminder.getDetails());
		try {
			initialize();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void initialize() throws Exception {
		initialize_original5();
		spinnerCategory.setSelection(categoryToIndex(reminder.getCategory()));
	}
	@Override
	protected void persist(Reminder reminder) {
		try {
			Controller.instance(getApplicationContext()).addReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** modified by dPriority
	 */
	private void reminderFromIntent_original0(Intent intent) throws Exception {
		String date = intent.getStringExtra("date");
		String hour = intent.getStringExtra("hour");
		reminder.setDate(date);
		reminder.setHour(hour);
	}
	/*** modified by dPriority
	 */
	private void initialize_original2() throws Exception {
		updateSpinnerDateHour(spinnerDate, reminder.getDate());
		updateDateFromString(reminder.getDate());
		updateSpinnerDateHour(spinnerTime, reminder.getHour());
		updateTimeFromString(reminder.getHour());
	}
	/*** added by dStaticCategory
	 */
	private Category newCategory = null;
	/*** added by dStaticCategory
	 */
	private void setNewCategory(Intent intent) throws Exception {
		String categoryName = intent.getStringExtra("category_name");
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		for(Category c : categories) {
			if(c.getName().equals(categoryName)) {
				newCategory = c;
				break;
			}
		}
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void reminderFromIntent_original4(Intent intent) throws Exception {
		reminderFromIntent_original0(intent);
		String priority = intent.getStringExtra("priority");
		reminder.setPriority(Priority.fromCode(Integer.parseInt(priority)));
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void initialize_original5() throws Exception {
		initialize_original2();
		spinnerPriority.setSelection(reminder.getPriority());
	}
	/*** added by dStaticCategory
	 */
	@Override
	protected List<Category> getCategories() throws Exception {
		List<Category> categories = super.getCategories();
		return categories;
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
}
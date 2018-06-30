package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view;

import java.util.Arrays;
import java.util.List;
import reminder.fixeddateprioritystaticcategory.util.Mask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.controller.Controller;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.InvalidDateException;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.InvalidFormatException;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.InvalidTextException;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Reminder;
import br.unb.cic.reminders2.R;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Priority;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dPriority* modified by
dStaticCategory
 */
public class ReminderAddActivity extends Activity {
	private EditText edtReminder, edtDetails, edtDate, edtHour;
	private Button btnSave, btnCancel;
	private boolean editingReminder;
	private Long previewReminderId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_add);
		Reminder existingReminder = getReminderFromIntent();
		if(existingReminder == null) {
			editingReminder = true;
			Reminder editReminder = getExistingReminder();
			initialize(editReminder);
		}
		else {
			editingReminder = false;
			initialize(existingReminder);
		}
		configureActionListener();
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void configureActionListener() {
		configureActionListener_original12();
		addListenerToSpinnerCategory();
	}
	private void addListenerToBtnSave() {
		btnSave.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					try {
						Reminder reminder = createReminder();
						if(editingReminder) {
							reminder.setId(previewReminderId);
							Controller.instance(getApplicationContext()).updateReminder(reminder);
						}
						else {
							Controller.instance(getApplicationContext()).addReminder(reminder);
						}
						finish();
					}
					catch(Exception e) {
						Log.e("ReminderAddActivity", e.getMessage());
						e.printStackTrace();
					}
				}
			});
	}
	private void addListenerToBtnCancel() {
		btnCancel.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
	}
	private Reminder createReminder() {
		Reminder reminder = new Reminder();
		try {
			reminder = createReminderAux();
			reminder.setText(edtReminder.getText().toString());
			reminder.setDetails(edtDetails.getText().toString());
		}
		catch(InvalidTextException e) {
			Toast.makeText(getApplicationContext(), "Invalid text.",
				Toast.LENGTH_SHORT).show();
		}
		catch(InvalidDateException e) {
			Toast.makeText(getApplicationContext(), "Invalid date.",
				Toast.LENGTH_SHORT).show();
		}
		catch(InvalidHourException e) {
			Toast.makeText(getApplicationContext(), "Invalid time.",
				Toast.LENGTH_SHORT).show();
		}
		return reminder;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private Reminder createReminderAux() {
		Reminder reminder = createReminderAux_original13();
		reminder.setCategory(selectedCategory);
		return reminder;
	}
	private Reminder getExistingReminder() {
		Reminder reminder = null;
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if(Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)) {
			previewReminderId = intent.getLongExtra("id", 0);
			String text = intent.getStringExtra("text");
			reminder = createReminderExisting(intent);
			reminder.setText(text);
			reminder.setId(previewReminderId);
		}
		return reminder;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private Reminder createReminderExisting(Intent intent) {
		Reminder reminder = createReminderExisting_original14(intent);
		String categoryName = intent.getStringExtra("category_name");
		String categoryId = intent.getStringExtra("category_id");
		Category category = new Category();
		category.setName(categoryName);
		category.setId(Long.parseLong(categoryId));
		reminder.setCategory(category);
		return reminder;
	}
	private Reminder getReminderFromIntent() {
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if("br.com.positivo.reminders.ADD_REMINDER".equals(action) &&
			"text/plain".equals(type)) {
			try {
				String text = intent.getStringExtra("text");
				String details = intent.getStringExtra("details");
				Reminder reminder = createReminderIntent(intent);
				reminder.setText(text);
				reminder.setDetails(details);
				return reminder;
			}
			catch(InvalidFormatException e) {
			}
		}
		return null;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private Reminder createReminderIntent(Intent intent) {
		Reminder reminder = createReminderIntent_original15(intent);
		String category = intent.getStringExtra("category");
		Category auxCategory = new Category();
		auxCategory.setName(category);
		reminder.setCategory(auxCategory);
		return reminder;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void initialize(Reminder reminder) {
		try {
			spinnerCategory = getSpinnerCategory();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		initialize_original16(reminder);
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void updateFieldsFromReminder(Reminder reminder) throws Exception {
		updateFieldsFromReminder_original17(reminder);
		spinnerCategory.setSelection(categoryToIndex(reminder.getCategory()));
	}
	/*** added by dPriority
	 */
	private Priority selectedPriority;
	/*** added by dPriority
	 */
	private Spinner spinnerPriority;
	/*** modified by dPriority
	 */
	private void configureActionListener_original0() {
		addListenerToBtnSave();
		addListenerToBtnCancel();
	}
	/*** added by dPriority
	 */
	private void addListenerToSpinnerPriority() {
		spinnerPriority.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<? extends Object> parent, View view,
					int pos, long id) {
					selectedPriority = ( Priority ) parent.getItemAtPosition(pos);
				}
				public void onNothingSelected(AdapterView<? extends Object> parent) {
				}
			});
	}
	/*** modified by dPriority
	 */
	private Reminder createReminderAux_original2() {
		Reminder reminder = new Reminder();
		reminder.setDate(edtDate.getText().toString());
		reminder.setHour(edtHour.getText().toString());
		return reminder;
	}
	/*** modified by dPriority
	 */
	private Reminder createReminderExisting_original4(Intent intent) {
		Reminder reminder = new Reminder();
		String date = intent.getStringExtra("date");
		String hour = intent.getStringExtra("hour");
		reminder.setDate(date);
		reminder.setHour(hour);
		return reminder;
	}
	/*** modified by dPriority
	 */
	private Reminder createReminderIntent_original6(Intent intent) {
		Reminder reminder = new Reminder();
		String date = intent.getStringExtra("date");
		String hour = intent.getStringExtra("hour");
		reminder.setDate(date);
		reminder.setHour(hour);
		return reminder;
	}
	/*** modified by dPriority
	 */
	private void initialize_original8(Reminder reminder) {
		try {
			edtReminder = ( EditText ) findViewById(R.id.edtReminder);
			edtDetails = ( EditText ) findViewById(R.id.edtDetails);
			if(reminder != null) {
				updateFieldsFromReminder(reminder);
			}
			btnSave = ( Button ) findViewById(R.id.btnSave);
			btnCancel = ( Button ) findViewById(R.id.btnCancel);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** modified by dPriority
	 */
	private void updateFieldsFromReminder_original10(Reminder reminder) throws
	Exception {
		edtReminder.setText(reminder.getText());
		edtDetails.setText(reminder.getDetails());
		edtDate.setText(reminder.getDate());
		edtHour.setText(reminder.getHour());
	}
	/*** added by dPriority
	 */
	private Spinner getSpinnerPriority() {
		Spinner spinner = ( Spinner ) findViewById(R.id.spinnerPriorities);
		SpinnerAdapterGenerator<Priority> adapterPriorityGenerator = new
		SpinnerAdapterGenerator<Priority>();
		List<Priority> priorityValues = Arrays.asList(Priority.values());
		ArrayAdapter<Priority> priorityArrayAdapter =
		adapterPriorityGenerator.getSpinnerAdapter(priorityValues, this);
		spinner.setAdapter(priorityArrayAdapter);
		spinner.setSelection(Priority.NORMAL.getCode());
		return spinner;
	}
	/*** added by dStaticCategory
	 */
	private Category selectedCategory;
	/*** added by dStaticCategory
	 */
	private Spinner spinnerCategory;
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void configureActionListener_original12() {
		configureActionListener_original0();
		addListenerToSpinnerPriority();
	}
	/*** added by dStaticCategory
	 */
	private void addListenerToSpinnerCategory() {
		spinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<? extends Object> parent, View view,
					int pos, long id) {
					selectedCategory = ( Category ) parent.getItemAtPosition(pos);
				}
				public void onNothingSelected(AdapterView<? extends Object> parent) {
				}
			});
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private Reminder createReminderAux_original13() {
		Reminder reminder = createReminderAux_original2();
		reminder.setPriority(selectedPriority);
		return reminder;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private Reminder createReminderExisting_original14(Intent intent) {
		Reminder reminder = createReminderExisting_original4(intent);
		String priority = intent.getStringExtra("priority");
		reminder.setPriority(Priority.fromCode(Integer.parseInt(priority, 10)));
		return reminder;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private Reminder createReminderIntent_original15(Intent intent) {
		Reminder reminder = createReminderIntent_original6(intent);
		String priority = intent.getStringExtra("priority");
		return reminder;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void initialize_original16(Reminder reminder) {
		spinnerPriority = getSpinnerPriority();
		initialize_original8(reminder);
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	private void updateFieldsFromReminder_original17(Reminder reminder) throws
	Exception {
		updateFieldsFromReminder_original10(reminder);
		spinnerPriority.setSelection(reminder.getPriority());
	}
	/*** added by dStaticCategory
	 */
	private Spinner getSpinnerCategory() throws Exception {
		Spinner spinner = ( Spinner ) findViewById(R.id.spinnerCategories);
		SpinnerAdapterGenerator<Category> adapterCategoryGenerator = new
		SpinnerAdapterGenerator<Category>();
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		spinner.setAdapter(adapterCategoryGenerator.getSpinnerAdapter(categories,
				this));
		return spinner;
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
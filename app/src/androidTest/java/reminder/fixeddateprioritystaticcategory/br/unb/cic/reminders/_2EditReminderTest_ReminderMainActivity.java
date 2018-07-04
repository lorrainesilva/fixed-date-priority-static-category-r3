package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import br.unb.cic.reminders2.R;
import reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view.EditReminderActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class _2EditReminderTest_ReminderMainActivity extends
        ActivityInstrumentationTestCase2<ReminderMainActivity> {
    @Rule
    public ActivityTestRule<ReminderMainActivity> mActivityTestRule =
            new ActivityTestRule<>(ReminderMainActivity.class);

    private Solo solo;
    private Activity activity;

    public _2EditReminderTest_ReminderMainActivity() {
        super(ReminderMainActivity.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        this.activity = this.getActivity();
        this.solo = new Solo(getInstrumentation(), this.activity);
//        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
//                mActivityTestRule.getActivity());
    }

    @Override
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testLongClickOnReminderEdit_shouldStartEditReminderUi() throws Exception {
        solo.unlockScreen();
        assertTrue(solo.searchText("Read a book"));
        solo.clickLongOnText("Read a book");
        solo.clickOnText("Edit");
        solo.assertCurrentActivity("Expected Edit Reminder Activity", EditReminderActivity.class);
    }

    public void testEditValidReminder1_shouldBeShownOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "Read a book" reminder
        assertTrue(solo.searchText("Read a book"));

        // Long click on reminder
        solo.clickLongOnText("Read a book");

        // Click on edit option
        solo.clickOnText("Edit");

        // Same process of EditText for reminder title in EditText for reminder details
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "The Comedy");

        // Click on spinner of date
        solo.clickOnView(solo.getView(R.id.spinnerDate));
        // Click on "+ Select" option of date spinner
        solo.clickOnText("+ Select");

        // Wait for DatePickerFragment
        solo.waitForFragmentByTag("datePicker");

        // Get the DatePickerFragment
        DialogFragment dateFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("datePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(dateFragment);
        while (!(isShown(dateFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(dateFragment));
            solo.sleep(100);
        }

        // Type cast for DatePickerDialog
        DatePickerDialog datePicker = (DatePickerDialog) dateFragment.getDialog();;

        // Get a date in future
        Calendar dayInPast = DayInPast();

        // Set the date
        assertNotNull(datePicker);
        solo.setDatePicker(datePicker.getDatePicker(), dayInPast.get(Calendar.YEAR),
                dayInPast.get(Calendar.MONTH), dayInPast.get(Calendar.DAY_OF_MONTH));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of time
        solo.clickOnView(solo.getView(R.id.spinnerTime));
        // Click on "+ Select" option of time spinner
        solo.clickOnText("+ Select");

        // Wait for TimePickerFragment
        solo.waitForFragmentByTag("timePicker");

        // Get the DatePickerFragment
        DialogFragment timeFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("timePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(timeFragment);
        while (!(isShown(timeFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(timeFragment));
            solo.sleep(100);
        }

        // Type cast for TimePickerDialog
        TimePickerDialog timePicker = (TimePickerDialog) timeFragment.getDialog();

        // Set the time
        assertNotNull(timePicker);
        timePicker.updateTime(dayInPast.get(Calendar.HOUR_OF_DAY), dayInPast.get(Calendar.MINUTE));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of priority
        solo.clickOnView(solo.getView(R.id.spinnerPriorities));
        // Click on "Important" option of priority spinner
        solo.clickOnText("Important");

        // Click on spinner of categories
        solo.clickOnView(solo.getView(R.id.spinnerCategories));
        // Click on "College" option of categories spinner
        solo.clickOnText("College");

        // Click on "Save" button
        solo.clickOnText("Save");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be edited
        assertTrue(solo.searchText("Read a book"));

        // Verify if the reminder have the correct priority
        solo.clickOnText("Important");
        assertTrue(solo.searchText("Read a book"));

        // Verify if the reminder have the correct category
        solo.clickOnText("College");
        assertTrue(solo.searchText("Read a book"));
    }

    public void testEditValidReminder2_shouldBeShownOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "College work" reminder
        assertTrue(solo.searchText("College work"));

        // Long click on reminder
        solo.clickLongOnText("College work");

        // Click on edit option
        solo.clickOnText("Edit");

        // Search for EditText of reminder title
        EditText etReminderTitle = (EditText) solo.getView(R.id.edtReminder);

        // Clean the reminder title EditText
        solo.clearEditText(etReminderTitle);

        // Enter with a title for reminder
        solo.enterText(etReminderTitle,"College home work");

        // Same process of EditText for reminder title in EditText for reminder details
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "Present the work on automatized tests");

        // Click on spinner of date
        solo.clickOnView(solo.getView(R.id.spinnerDate));
        // Click on "+ Select" option of date spinner
        solo.clickOnText("+ Select");

        // Wait for DatePickerFragment
        solo.waitForFragmentByTag("datePicker");

        // Get the DatePickerFragment
        DialogFragment dateFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("datePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(dateFragment);
        while (!(isShown(dateFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(dateFragment));
            solo.sleep(100);
        }

        // Type cast for DatePickerDialog
        DatePickerDialog datePicker = (DatePickerDialog) dateFragment.getDialog();;

        // Get a date in future
        Calendar dayInFuture = DayInFuture();

        // Set the date
        assertNotNull(datePicker);
        solo.setDatePicker(datePicker.getDatePicker(), dayInFuture.get(Calendar.YEAR),
                dayInFuture.get(Calendar.MONTH), dayInFuture.get(Calendar.DAY_OF_MONTH));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of time
        solo.clickOnView(solo.getView(R.id.spinnerTime));
        // Click on "+ Select" option of time spinner
        solo.clickOnText("+ Select");

        // Wait for TimePickerFragment
        solo.waitForFragmentByTag("timePicker");

        // Get the DatePickerFragment
        DialogFragment timeFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("timePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(timeFragment);
        while (!(isShown(timeFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(timeFragment));
            solo.sleep(100);
        }

        // Type cast for TimePickerDialog
        TimePickerDialog timePicker = (TimePickerDialog) timeFragment.getDialog();

        // Set the time
        assertNotNull(timePicker);
        timePicker.updateTime(dayInFuture.get(Calendar.HOUR_OF_DAY), dayInFuture.get(Calendar.MINUTE));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of priority
        solo.clickOnView(solo.getView(R.id.spinnerPriorities));
        // Click on "Urgent" option of priority spinner
        solo.clickOnText("No Priority");

        // Click on spinner of categories
        solo.clickOnView(solo.getView(R.id.spinnerCategories));
        // Click on "College" option of categories spinner
        solo.clickOnText("Personal");

        // Click on "Save" button
        solo.clickOnText("Save");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be edited
        assertTrue(solo.searchText("College home work"));

        // Verify if the reminder have the correct priority
        solo.clickOnText("No Priority");
        assertTrue(solo.searchText("College home work"));

        // Verify if the reminder have the correct category
        solo.clickOnText("Personal");
        assertTrue(solo.searchText("College"));
    }

    public void testEditValidReminder3_shouldBeShownOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "Meeting" reminder
        assertTrue(solo.searchText("Meeting"));

        // Long click on reminder
        solo.clickLongOnText("Meeting");

        // Click on edit option
        solo.clickOnText("Edit");

        // Search for EditText of reminder title
        EditText etReminderTitle = (EditText) solo.getView(R.id.edtReminder);

        // Clean the reminder title EditText
        solo.clearEditText(etReminderTitle);

        // Enter with a title for reminder
        solo.enterText(etReminderTitle,"Project meeting");

        // Same process of EditText for reminder title in EditText for reminder details
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "Talk about Visitor");

        // Click on spinner of date
        solo.clickOnView(solo.getView(R.id.spinnerDate));
        // Click on "+ Select" option of date spinner
        solo.clickOnText("+ Select");

        // Wait for DatePickerFragment
        solo.waitForFragmentByTag("datePicker");

        // Get the DatePickerFragment
        DialogFragment dateFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("datePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(dateFragment);
        while (!(isShown(dateFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(dateFragment));
            solo.sleep(100);
        }

        // Type cast for DatePickerDialog
        DatePickerDialog datePicker = (DatePickerDialog) dateFragment.getDialog();;

        // Get a date in future
        Calendar dayInPresent = DayInPresent();

        // Set the date
        assertNotNull(datePicker);
        solo.setDatePicker(datePicker.getDatePicker(), dayInPresent.get(Calendar.YEAR),
                dayInPresent.get(Calendar.MONTH), dayInPresent.get(Calendar.DAY_OF_MONTH));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of time
        solo.clickOnView(solo.getView(R.id.spinnerTime));
        // Click on "+ Select" option of time spinner
        solo.clickOnText("+ Select");

        // Wait for TimePickerFragment
        solo.waitForFragmentByTag("timePicker");

        // Get the DatePickerFragment
        DialogFragment timeFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("timePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(timeFragment);
        while (!(isShown(timeFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(timeFragment));
            solo.sleep(100);
        }

        // Type cast for TimePickerDialog
        TimePickerDialog timePicker = (TimePickerDialog) timeFragment.getDialog();

        // Set the time
        assertNotNull(timePicker);
        timePicker.updateTime(dayInPresent.get(Calendar.HOUR_OF_DAY), dayInPresent.get(Calendar.MINUTE));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of priority
        solo.clickOnView(solo.getView(R.id.spinnerPriorities));
        // Click on "Important" option of priority spinner
        solo.clickOnText("Urgent");

        // Click on spinner of categories
        solo.clickOnView(solo.getView(R.id.spinnerCategories));
        // Click on "Job" option of categories spinner
        solo.clickOnText("Job");

        // Click on "Save" button
        solo.clickOnText("Save");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be edited
        assertTrue(solo.searchText("Project meeting"));

        // Verify if the reminder have the correct priority
        solo.clickOnText("Urgent");
        assertTrue(solo.searchText("Project meeting"));

        // Verify if the reminder have the correct category
        solo.clickOnText("Job");
        assertTrue(solo.searchText("Project meeting"));
    }

    public void testEditInvalidReminder_shouldNotBeShownOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "Meeting" reminder
        assertTrue(solo.searchText("Read a book"));

        // Long click on reminder
        solo.clickLongOnText("Read a book");

        // Click on edit option
        solo.clickOnText("Edit");

        // Search for EditText of reminder title
        EditText etReminderTitle = (EditText) solo.getView(R.id.edtReminder);

        // Clean the reminder title EditText
        solo.clearEditText(etReminderTitle);

        // Enter with a title for reminder
        solo.enterText(etReminderTitle,"");

        // Same process of EditText for reminder title in EditText for reminder details
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "V for vendetta");

        // Click on spinner of date
        solo.clickOnView(solo.getView(R.id.spinnerDate));
        // Click on "+ Select" option of date spinner
        solo.clickOnText("+ Select");

        // Wait for DatePickerFragment
        solo.waitForFragmentByTag("datePicker");

        // Get the DatePickerFragment
        DialogFragment dateFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("datePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(dateFragment);
        while (!(isShown(dateFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(dateFragment));
            solo.sleep(100);
        }

        // Type cast for DatePickerDialog
        DatePickerDialog datePicker = (DatePickerDialog) dateFragment.getDialog();;

        // Get a date in future
        Calendar dayInPresent = DayInFuture();

        // Set the date
        assertNotNull(datePicker);
        solo.setDatePicker(datePicker.getDatePicker(), dayInPresent.get(Calendar.YEAR),
                dayInPresent.get(Calendar.MONTH), dayInPresent.get(Calendar.DAY_OF_MONTH));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of time
        solo.clickOnView(solo.getView(R.id.spinnerTime));
        // Click on "+ Select" option of time spinner
        solo.clickOnText("+ Select");

        // Wait for TimePickerFragment
        solo.waitForFragmentByTag("timePicker");

        // Get the DatePickerFragment
        DialogFragment timeFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("timePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(timeFragment);
        while (!(isShown(timeFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(timeFragment));
            solo.sleep(100);
        }

        // Type cast for TimePickerDialog
        TimePickerDialog timePicker = (TimePickerDialog) timeFragment.getDialog();

        // Set the time
        assertNotNull(timePicker);
        timePicker.updateTime(dayInPresent.get(Calendar.HOUR_OF_DAY), dayInPresent.get(Calendar.MINUTE));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of priority
        solo.clickOnView(solo.getView(R.id.spinnerPriorities));
        // Click on "Important" option of priority spinner
        solo.clickOnText("Important");

        // Click on spinner of categories
        solo.clickOnView(solo.getView(R.id.spinnerCategories));
        // Click on "Job" option of categories spinner
        solo.clickOnText("College");

        // Click on "Save" button
        solo.clickOnText("Save");

        // Verify if message of error was showed
        solo.waitForText("Invalid text.");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);
    }

    public void testCancelEditValidReminder_shouldNotBeShownOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "Meeting" reminder
        assertTrue(solo.searchText("Read a book"));

        // Long click on reminder
        solo.clickLongOnText("Read a book");

        // Click on edit option
        solo.clickOnText("Edit");

        // Search for EditText of reminder title
        EditText etReminderTitle = (EditText) solo.getView(R.id.edtReminder);

        // Clean the reminder title EditText
        solo.clearEditText(etReminderTitle);

        // Enter with a title for reminder
        solo.enterText(etReminderTitle,"Read another book");

        // Same process of EditText for reminder title in EditText for reminder details
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "V for vendetta");

        // Click on spinner of date
        solo.clickOnView(solo.getView(R.id.spinnerDate));
        // Click on "+ Select" option of date spinner
        solo.clickOnText("+ Select");

        // Wait for DatePickerFragment
        solo.waitForFragmentByTag("datePicker");

        // Get the DatePickerFragment
        DialogFragment dateFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("datePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(dateFragment);
        while (!(isShown(dateFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(dateFragment));
            solo.sleep(100);
        }

        // Type cast for DatePickerDialog
        DatePickerDialog datePicker = (DatePickerDialog) dateFragment.getDialog();;

        // Get a date in future
        Calendar dayInPresent = DayInPast();

        // Set the date
        assertNotNull(datePicker);
        solo.setDatePicker(datePicker.getDatePicker(), dayInPresent.get(Calendar.YEAR),
                dayInPresent.get(Calendar.MONTH), dayInPresent.get(Calendar.DAY_OF_MONTH));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of time
        solo.clickOnView(solo.getView(R.id.spinnerTime));
        // Click on "+ Select" option of time spinner
        solo.clickOnText("+ Select");

        // Wait for TimePickerFragment
        solo.waitForFragmentByTag("timePicker");

        // Get the DatePickerFragment
        DialogFragment timeFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("timePicker");

        // Wait until DatePickerFragment is visible on screen
        assertNotNull(timeFragment);
        while (!(isShown(timeFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(timeFragment));
            solo.sleep(100);
        }

        // Type cast for TimePickerDialog
        TimePickerDialog timePicker = (TimePickerDialog) timeFragment.getDialog();

        // Set the time
        assertNotNull(timePicker);
        timePicker.updateTime(dayInPresent.get(Calendar.HOUR_OF_DAY), dayInPresent.get(Calendar.MINUTE));

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on spinner of priority
        solo.clickOnView(solo.getView(R.id.spinnerPriorities));
        // Click on "Important" option of priority spinner
        solo.clickOnText("Important");

        // Click on spinner of categories
        solo.clickOnView(solo.getView(R.id.spinnerCategories));
        // Click on "Job" option of categories spinner
        solo.clickOnText("College");

        // Click on "Cancel" button
        solo.clickOnText("Cancel");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        solo.sleep(1000);

        // Verify if the reminder was be edited
        assertFalse(solo.searchText("Read another book"));

        // Verify if the reminder have the correct priority
        solo.clickOnText("Important");
        assertFalse(solo.searchText("Read another book"));

        // Verify if the reminder have the correct category
        solo.clickOnText("College");
        assertFalse(solo.searchText("Read another book"));
    }

    // Auxilliar function for verify if DialogFragment can be used
    private boolean isShown(DialogFragment fragment) {
        if (fragment.getDialog() == null) {
            return false;
        } else {
            return true;
        }
    }

    private Calendar DayInFuture() {
        Calendar dayInFuture = Calendar.getInstance();
        Date currentDay = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDay);
        if (cal.get(Calendar.MONTH) < 11) {
            dayInFuture.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 9, 30);
        } else {
            dayInFuture.set(cal.get(Calendar.YEAR) + 1, 0, 1, 9, 30);
        }
        return dayInFuture;
    }

    private Calendar DayInPresent() {
        Calendar dayInPresent = Calendar.getInstance();
        Date currentDay = new Date();
        dayInPresent.setTime(currentDay);
        if (dayInPresent.get(Calendar.HOUR_OF_DAY) < 23) {
            dayInPresent.set(dayInPresent.get(Calendar.YEAR), dayInPresent.get(Calendar.MONTH),
                    dayInPresent.get(Calendar.DAY_OF_MONTH), dayInPresent.get(Calendar.HOUR_OF_DAY) + 1, 30);
        } else {
            dayInPresent.set(dayInPresent.get(Calendar.YEAR), dayInPresent.get(Calendar.MONTH),
                    dayInPresent.get(Calendar.DAY_OF_MONTH), dayInPresent.get(Calendar.HOUR_OF_DAY), 59);
        }
        return dayInPresent;
    }

    private Calendar DayInPast() {
        Calendar dayInPast = Calendar.getInstance();
        Date currentDay = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDay);
        if (cal.get(Calendar.YEAR) > 0) {
            dayInPast.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, 1, 9, 30);
        } else {
            dayInPast.set(cal.get(Calendar.YEAR) - 1, 11, 1, 9, 30);
        }
        return dayInPast;
    }
}

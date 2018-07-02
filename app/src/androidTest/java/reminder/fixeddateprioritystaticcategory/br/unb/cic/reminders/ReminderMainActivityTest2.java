package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.unb.cic.reminders2.R;
import reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view.AddReminderActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ReminderMainActivityTest2 {
    @Rule
    public ActivityTestRule<ReminderMainActivity> mActivityTestRule =
            new ActivityTestRule<>(ReminderMainActivity.class);

    private Solo solo;

    @Before
    public void setup() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                mActivityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void clickOnAddReminderButton_shouldStartAddReminderUi() {
        solo.unlockScreen();
        solo.clickOnView(solo.getView(R.id.menu_addReminder));
        solo.assertCurrentActivity("Expected Add Reminder Activity", AddReminderActivity.class);
    }

    @Test
    public void createValidReminder_shouldBeShownOnReminderMainUi() {
        // Unlock the screen
        solo.unlockScreen();

        // Click on "+" button
        solo.clickOnView(solo.getView(R.id.menu_addReminder));

        // Search for EditText of reminder title
        EditText etReminderTitle = (EditText) solo.getView(R.id.edtReminder);

        // Clean the reminder title EditText
        solo.clearEditText(etReminderTitle);

        // Enter with a title for reminder
        solo.enterText(etReminderTitle, "Read a book");

        // Same process of EditText for reminder title in EditText for reminder details
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "The Homer Odyssey");

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
        DatePickerDialog datePicker = (DatePickerDialog) dateFragment.getDialog();

        // Set the date
        assertNotNull(datePicker);
        solo.setDatePicker(datePicker.getDatePicker(), 2018, 8, 7);

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
        timePicker.updateTime(23, 8);

        // Click on "OK" button
        solo.clickOnText("OK");

        // Click on "Save" button
        solo.clickOnText("Save");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be created
        assertTrue(solo.searchText("Read a book"));
    }


    // Auxilliar function for verify if DialogFragment can be used
    public boolean isShown(DialogFragment fragment){
        if (fragment.getDialog() == null) {
            return false;
        } else {
            return true;
        }
    }
}

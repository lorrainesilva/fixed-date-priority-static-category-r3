package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.EditText;

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
        solo.unlockScreen();
        solo.clickOnView(solo.getView(R.id.menu_addReminder));
        EditText etReminderTitle = (EditText) solo.getView(R.id.edtReminder);
        solo.clearEditText(etReminderTitle);
        solo.enterText(etReminderTitle, "Read a book");
        EditText etReminderDetails = (EditText) solo.getView(R.id.edtDetails);
        solo.clearEditText(etReminderDetails);
        solo.enterText(etReminderDetails, "The Homer Odyssey");
        solo.clickOnView(solo.getView(R.id.spinnerDate));
        solo.clickOnText("+ Select");
        solo.waitForFragmentByTag("datePicker");

        DialogFragment dateStartFragment = (DialogFragment) solo.getCurrentActivity()
                .getFragmentManager().findFragmentByTag("datePicker");

        // Wait until dialog fragment is visible on screen
        assertNotNull(dateStartFragment);
        while (!(isShown(dateStartFragment))) {
            System.out.println("Dialog fragment visible: " + isShown(dateStartFragment));
            solo.sleep(100);
        }

        DatePickerDialog dateStartPicker = (DatePickerDialog) dateStartFragment.getDialog();
        assertNotNull(dateStartPicker);
        solo.setDatePicker(dateStartPicker.getDatePicker(), 2016, 11, 16);

//        DialogFragment dateStartFragment = (DialogFragment) solo.getCurrentActivity()
//                .getFragmentManager().findFragmentByTag("datePicker");
//        DatePickerDialog dateStartPicker = (DatePickerDialog) dateStartFragment.getDialog();
//        solo.setDatePicker(dateStartPicker.getDatePicker(), 2016, 11, 16);

        solo.clickOnText("OK");
        solo.clickOnText("Save");
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);
        assertTrue(solo.searchText("Read a book"));
    }

    public boolean isShown(DialogFragment fragment){
        if (fragment.getDialog() == null) {
            return false;
        } else {
            return true;
        }
    }
}

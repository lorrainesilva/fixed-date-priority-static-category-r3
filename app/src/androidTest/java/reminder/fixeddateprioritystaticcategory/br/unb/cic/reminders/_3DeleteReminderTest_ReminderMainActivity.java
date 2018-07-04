package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class _3DeleteReminderTest_ReminderMainActivity extends
        ActivityInstrumentationTestCase2<ReminderMainActivity> {
    @Rule
    public ActivityTestRule<ReminderMainActivity> mActivityTestRule =
            new ActivityTestRule<>(ReminderMainActivity.class);

    private Solo solo;
    private Activity activity;

    public _3DeleteReminderTest_ReminderMainActivity() {
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

    public void testDeleteValidReminder1_shouldBeRemovedOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "Read a book" reminder
        assertTrue(solo.searchText("Read a book"));

        // Long click on reminder
        solo.clickLongOnText("Read a book");

        // Click on delete option
        solo.clickOnText("Delete");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be deleted
        assertFalse(solo.searchText("Read a book"));

        solo.clickOnText("Important");
        assertFalse(solo.searchText("Read a book"));

        solo.clickOnText("College");
        assertFalse(solo.searchText("Read a book"));
    }

    public void testDeleteValidReminder2_shouldBeRemovedOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "College home work" reminder
        assertTrue(solo.searchText("College home work"));

        // Long click on reminder
        solo.clickLongOnText("College home work");

        // Click on delete option
        solo.clickOnText("Delete");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be deleted
        assertFalse(solo.searchText("College home work"));

        solo.clickOnText("No Priority");
        assertFalse(solo.searchText("College home work"));

        solo.clickOnText("Personal");
        assertFalse(solo.searchText("College home work"));
    }

    public void testDeleteValidReminder3_shouldBeRemovedOnReminderMainUi() throws Exception {
        // Unlock the screen
        solo.unlockScreen();

        // Search for "Project meeting" reminder
        assertTrue(solo.searchText("Project meeting"));

        // Long click on reminder
        solo.clickLongOnText("Project meeting");

        // Click on delete option
        solo.clickOnText("Delete");

        // Verify if the the current activity is ReminderMainActivity
        solo.assertCurrentActivity("Expected Reminder Main Activity", ReminderMainActivity.class);

        // Verify if the reminder was be deleted
        assertFalse(solo.searchText("Project meeting"));

        solo.clickOnText("Urgent");
        assertFalse(solo.searchText("Project meeting"));

        solo.clickOnText("Job");
        assertFalse(solo.searchText("Project meeting"));
    }
}

package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestSuite;

public class AllTests extends ActivityInstrumentationTestCase2<Activity> {
    public AllTests(Class<Activity> activityClass) {
        super(activityClass);
    }

    public static TestSuite suite() {
        TestSuite tSuite = new TestSuite();
        tSuite.addTestSuite(_1CreateReminderTest_ReminderMainActivity.class);
        tSuite.addTestSuite(_2EditReminderTest_ReminderMainActivity.class);
        tSuite.addTestSuite(_3DeleteReminderTest_ReminderMainActivity.class);
        return tSuite;
    }

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() throws Exception {
    }
}

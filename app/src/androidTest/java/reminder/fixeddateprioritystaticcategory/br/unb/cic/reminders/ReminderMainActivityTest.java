package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.unb.cic.reminders2.R;

@RunWith(AndroidJUnit4.class)
public class ReminderMainActivityTest {
    @Rule
    public ActivityTestRule<ReminderMainActivity> mReminderMainActivityActivityTestRule =
            new ActivityTestRule<ReminderMainActivity>(ReminderMainActivity.class);

    @Test
    public void clickOnAddReminderButton_ShowsAddReminderUi() {
        onView(withId(R.id.menu_addReminder)).perform(click());
        onView(withId(R.layout.reminder_add)).check(matches(isDisplayed()));
    }
}

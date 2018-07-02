//package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders;
//
//import android.app.DatePickerDialog;
//import android.app.DialogFragment;
//import android.support.test.espresso.contrib.PickerActions;
//import android.support.test.espresso.matcher.RootMatchers;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import static android.support.test.espresso.Espresso.onData;
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.replaceText;
//import static android.support.test.espresso.action.ViewActions.scrollTo;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//import static org.hamcrest.Matchers.anything;
//import static org.hamcrest.Matchers.instanceOf;
//import static org.hamcrest.Matchers.is;
//
//import org.hamcrest.CoreMatchers;
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.Matchers;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import br.unb.cic.reminders2.BuildConfig;
//import br.unb.cic.reminders2.R;
//
//@RunWith(AndroidJUnit4.class)
//public class ReminderMainActivityTest {
//    @Rule
//    public ActivityTestRule<ReminderMainActivity> mReminderMainActivityActivityTestRule =
//            new ActivityTestRule<ReminderMainActivity>(ReminderMainActivity.class);
//
//    @Test
//    public void clickOnAddReminderButton_ShowsAddReminderUi() {
//        // Click on "+" button of Main Screen
//        onView(withId(R.id.menu_addReminder)).perform(click());
//        // Verify if the Add Reminder Screen is open
//        onView(withId(R.layout.reminder_add)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void createValidReminder_ShowCreatedReminderInMainUi() {
//        // Click on "+" button of Main Screen
//        onView(withId(R.id.menu_addReminder)).perform(click());
//
//        // Insert "Read a book" on reminder title
//        onView(withId(R.id.edtReminder)).perform(replaceText("Read a book"));
//
//        // Click on spinner date and select "+ Select option"
//        onView(allOf(withId(R.id.spinnerDate), childAtPosition(childAtPosition(
//                withClassName(is("android.widget.ScrollView")), 0),
//        5))).perform(scrollTo(), click());
//
//        //
//        onData(anything()).inAdapterView(childAtPosition(withClassName(
//                is("android.widget.PopupWindow$PopupBackgroundView")), 0)
//                ).atPosition(1).perform(click());
//
//        // Click on OK button on DatePicker
//        onView(withText("OK")).perform(click());
//
//        // Click on Save button
//        onView(allOf(withId(R.id.btnSave), withText("Save"), childAtPosition(
//                allOf(withId(R.id.linearLayout1), childAtPosition(
//                        withClassName(is("android.widget.RelativeLayout")),
//                        12)), 0))).perform(scrollTo(), click());
//
//        // Verify if the reminder was created
//        onView(withText("Read a book")).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void createReminderWithoutTitle_ShouldNotCreateTheReminder() {
//        // Click on "+" button of Main Screen
//        onView(withId(R.id.menu_addReminder)).perform(click());
//
//        // Click on spinner date and select "+ Select option"
////        onView(allOf(withId(R.id.spinnerDate), childAtPosition(childAtPosition(
////                withClassName(is("android.widget.ScrollView")), 0),
////                5))).perform(scrollTo(), click());
//
//        onView(withId(R.id.spinnerDate)).perform(click());
//        onView(withText("+ Select")).perform(click());
//
//        onData(anything()).inAdapterView(childAtPosition(
//                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
//                0)).atPosition(1).perform(typeText("16"));
//
//        // Click on OK button on DatePicker
//        onView(withText("OK")).perform(click());
//
//        // Click on Save button
//        onView(allOf(withId(R.id.btnSave), withText("Save"), childAtPosition(
//                allOf(withId(R.id.linearLayout1), childAtPosition(
//                        withClassName(is("android.widget.RelativeLayout")),
//                        12)), 0))).perform(scrollTo(), click());
//
//        // Verify if the reminder was created
////        onView(withText("Read a book")).check(matches(isDisplayed()));
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}

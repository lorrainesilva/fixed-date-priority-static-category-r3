package
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.controller;

import android.content.Context;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Priority;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model.Reminder;
/*** added by dPriority
 */
public class PriorityFilter extends ReminderFilter {
	private Priority priority;
	public PriorityFilter(Priority priority, Context context) {
		super(context);
		this.priority = priority;
	}
	@Override
	protected boolean selectReminder(Reminder r) {
		return r.getPriority() == priority.getCode();
	}
	@Override
	public String getName() {
		return priority.toString();
	}
}
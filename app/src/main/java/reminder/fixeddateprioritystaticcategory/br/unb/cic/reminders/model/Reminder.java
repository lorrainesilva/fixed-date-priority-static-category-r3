package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import reminder.fixeddateprioritystaticcategory.util.Patterns;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.DBTypes;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.annotations.Column;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.annotations.Entity;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.annotations.ForeignKey;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.view.InvalidHourException;
/*** added by dManageReminder* modified by dPriority* modified by
dStaticCategory
 */
@Entity(table = "REMINDER")
public class Reminder {
	@Column(column = "PK", primaryKey = true, type = DBTypes.LONG)
	private Long id;
	@Column(column = "TEXT", type = DBTypes.TEXT)
	private String text;
	@Column(column = "DETAILS", type = DBTypes.TEXT)
	private String details;
	@Column(column = "DATE", type = DBTypes.TEXT)
	private String date;
	@Column(column = "HOUR", type = DBTypes.TEXT)
	private String hour;
	@Column(column = "DONE", type = DBTypes.INT)
	private boolean done;
	public Reminder() {
	}
	public Reminder(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if(text == null || text.trim().equals("")) {
			throw new InvalidTextException(text);
		}
		this.text = text;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		if(details == null || details.trim().equals("")) {
			this.details = null;
		}
		else {
			this.details = details;
		}
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		if(!(date == null || date.equals("")) && ! checkFormat(date,
				Patterns.DATE_PATTERN)) {
			throw new InvalidDateException(date);
		}
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		if(!(hour == null || hour.equals("")) && ! checkFormat(hour,
				Patterns.HOUR_PATTERN)) {
			throw new InvalidHourException(hour);
		}
		this.hour = hour;
	}
	private boolean checkFormat(String date, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(date);
		return m.matches();
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	public boolean isValid() {
		return(category != null && isValid_original2());
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public int getDone() {
		return done ? 1 : 0;
	}
	public void setDone(int done) {
		this.done =(done == 0 ? false : true);
	}
	/*** added by dPriority
	 */
	@Column(column = "PRIORITY", type = DBTypes.INT)
	private Priority priority;
	/*** added by dPriority
	 */
	public int getPriority() {
		return priority.getCode();
	}
	/*** added by dPriority
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	/*** modified by dPriority
	 */
	public boolean isValid_original0() {
		return(text != null && date != null && hour != null);
	}
	/*** added by dStaticCategory
	 */
	@Column(column = "FK_CATEGORY", type = DBTypes.LONG)
	@ForeignKey(mappedBy = "id")
	private Category category;
	/*** added by dStaticCategory
	 */
	public Category getCategory() {
		return category;
	}
	/*** added by dStaticCategory
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/*** modified by dPriority* modified by dStaticCategory
	 */
	public boolean isValid_original2() {
		return(priority != null && isValid_original0());
	}
}
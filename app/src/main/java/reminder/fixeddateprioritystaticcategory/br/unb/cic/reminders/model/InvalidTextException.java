package reminder.fixeddateprioritystaticcategory.br.unb.cic.reminders.model;

/*** added by dManageReminder
 */
public class InvalidTextException extends InvalidFormatException {
	private static final long serialVersionUID = 7998188562654167391L;
	public InvalidTextException(Object o) {
		super("This text " + o);
	}
}
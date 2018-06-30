package
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import
reminder.fixeddateprioritystaticcategory.br.unb.cic.framework.persistence.DBTypes;
/*** added by dManageReminder
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	String column();
	boolean primaryKey() default false;
	DBTypes type();
}
/**
 * 
 */
package factories;

import java.util.Map;

import core.IUIBuilderFactory;
import core.UIBuilder;
import plugins.PhoneNumberPlugin;
import tripTracker.DialogFactory;
import tripTracker.StringConstants;
import tripTracker.Student;


/**
 * @author Lawrence
 *
 */
public class ClassFactory implements IUIBuilderFactory {

	public UIBuilder<Student> getClassObject(String title) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, StringConstants.STUDENTS);
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	
    	trip.registerAddDialog(DialogFactory.getAddStudentDialog(trip));
    	
		return trip; 
	}

	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		String title = (String)entries.get(StringConstants.NEW_CLASS);
		
		return getClassObject(title);
	}
	
}

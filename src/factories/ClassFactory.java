/**
 * 
 */
package factories;

import java.util.ArrayList;
import java.util.Map;

import core.IListener;
import core.INotifier;
import core.IUIBuilderFactory;
import core.UIBuilder;
import plugins.PhoneNumberPlugin;
import tripTracker.ClassManager;
import tripTracker.DialogFactory;
import tripTracker.StringConstants;
import tripTracker.Student;


/**
 * @author Lawrence
 *
 */
public class ClassFactory implements IUIBuilderFactory, INotifier {
	
	private static ArrayList<IListener> listeners = new ArrayList<IListener>();

	private static UIBuilder<Student> getClassObject(String title) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, StringConstants.STUDENTS);
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	
    	trip.registerAddDialog(DialogFactory.getAddStudentDialog(trip));
    	
		return trip; 
	}

	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		String title = (String)entries.get(StringConstants.NEW_CLASS);
		
		UIBuilder result = getClassObject(title);
		
		notifyListeners(result);
		
		return result;
	}

	@Override
	public void registerListener(IListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void notifyListeners(Object element) {
		for (IListener listener : listeners) {
			listener.update(element);
		}
	}
	
}

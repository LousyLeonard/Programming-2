/**
 * 
 */
package uiBuilders;

import java.util.ArrayList;
import java.util.Map;

import core.IListener;
import core.INotifier;
import core.IUIBuilderCreator;
import core.UIBuilder;
import dialogs.AddStudentDialogCreator;
import plugins.PhoneNumberPlugin;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;


/**
 * @author Lawrence
 *
 */
public class StudentClassCreator implements IUIBuilderCreator, INotifier {
	
	private static ArrayList<IListener> listeners = new ArrayList<IListener>();

	private static UIBuilder<Student> getClassObject(String title) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, TripTrackerConstants.STUDENTS);
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	
    	trip.registerAddDialog(new AddStudentDialogCreator());
    	
		return trip; 
	}

	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		String title = (String)entries.get(TripTrackerConstants.NEW_CLASS);
		
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

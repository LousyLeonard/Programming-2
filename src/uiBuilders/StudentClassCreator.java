/**
 * 
 */
package uiBuilders;

import java.io.Serializable;
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
 * A UIBuilder creator for a Class of Students instance.
 * 
 * @author Lawrence
 */
public class StudentClassCreator implements IUIBuilderCreator, INotifier, Serializable {
	
	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 8082890594706200604L;
	
	private static ArrayList<IListener> listeners = new ArrayList<IListener>();

	/**
	 * Gets a new instance of a Student Class UIBuilder.
	 * 
	 * @param title - The title of the UIBuilder.
	 * @return UIBuilder representing a student class.
	 */
	private static UIBuilder<Student> getClassObject(String title) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, TripTrackerConstants.STUDENTS);
    	
    	trip.addColumnPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	
    	trip.registerAddDialog(new AddStudentDialogCreator());
    	
		return trip; 
	}

	/* (non-Javadoc)
	 * @see core.IUIBuilderCreator#getNewInstance(java.util.Map)
	 */
	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		String title = (String)entries.get(TripTrackerConstants.NEW_CLASS);
		
		UIBuilder result = getClassObject(title);
		
		notifyListeners(result);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see core.INotifier#registerListener(core.IListener)
	 */
	@Override
	public void registerListener(IListener listener) {
		listeners.add(listener);
	}
	
	/* (non-Javadoc)
	 * @see core.INotifier#notifyListeners(java.lang.Object)
	 */
	@Override
	public void notifyListeners(Object element) {
		for (IListener listener : listeners) {
			listener.update(element);
		}
	}
	
}

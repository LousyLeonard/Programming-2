/**
 * 
 */
package tripTracker;

import java.io.Serializable;
import java.util.ArrayList;

import core.IListener;
import core.UIBuilder;
import uiBuilders.StudentClassCreator;

/**
 * A Singleton instance of the ClassManager. This hold information of all of the 
 * created classes in the application and provides access to them.
 * 
 * @author Lawrence
 */
public final class ClassManager implements IListener, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1368164896571011721L;

	private static ClassManager instance;
	
	// list of created classes.
	private static ArrayList<UIBuilder> classes;
		
	/**
	 * CONSTRUCTOR
	 */
	private ClassManager() {
		classes = new ArrayList<UIBuilder>();
				
		StudentClassCreator factoryInstance = new StudentClassCreator();
		factoryInstance.registerListener(this);
	}
	
	/**
	 * Get an instance of ClassManager.
	 * 
	 * @return the instance of ClassManager.
	 */
	public static ClassManager getInstance() {
		if(instance == null) {
			instance = new ClassManager();
		}
		return instance;
	}
	
	/**
	 * Get the Classes held in the manager.
	 * 
	 * @return The classes.
	 */
	public ArrayList<UIBuilder> getClasses() {
		return classes;
	}
	
	/**
	 * Add a class to the list of classes.
	 * 
	 * @param newClass - The class the add.
	 */
	private void addClass(UIBuilder newClass) {
		classes.add(newClass);
	}

	/* (non-Javadoc)
	 * @see core.IListener#update(java.lang.Object)
	 */
	@Override
	public void update(Object element) {
		addClass((UIBuilder)element);
	}

}

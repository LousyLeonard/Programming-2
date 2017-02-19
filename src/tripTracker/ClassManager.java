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
 * @author Lawrence
 *
 */
public final class ClassManager implements IListener, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1368164896571011721L;

	private static ClassManager instance;
	
	private static ArrayList<UIBuilder> classes;
		
	private ClassManager() {
		classes = new ArrayList<UIBuilder>();
				
		StudentClassCreator factoryInstance = new StudentClassCreator();
		factoryInstance.registerListener(this);
	}
	
	public static ClassManager getInstance() {
		if(instance == null) {
			instance = new ClassManager();
		}
		return instance;
	}
	
	public ArrayList<UIBuilder> getClasses() {
		return classes;
	}
	
	public void addClass(UIBuilder newClass) {
		classes.add(newClass);
	}

	@Override
	public void update(Object element) {
		addClass((UIBuilder)element);
	}

}

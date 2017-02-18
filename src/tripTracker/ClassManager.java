/**
 * 
 */
package tripTracker;

import java.util.ArrayList;

import core.IListener;
import core.INotifier;
import core.UIBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import factories.ClassFactory;

/**
 * @author Lawrence
 *
 */
public final class ClassManager implements INotifier, IListener {

	private static ClassManager instance;
	
	private static ArrayList<UIBuilder> classes;
	private static ArrayList<IListener> listeners;
	
	private static GenericExclusiveSelectionPanel selectionPanel;
	
	private ClassManager() {
		classes = new ArrayList<UIBuilder>();
		listeners = new ArrayList<IListener>();
		
		selectionPanel = new GenericExclusiveSelectionPanel(StringConstants.CLASS, classes);
		
		ClassFactory factoryInstance = new ClassFactory();
		factoryInstance.registerListener(this);
	}
	
	public static ClassManager getInstance() {
		if(instance == null) {
			instance = new ClassManager();
		}
		return instance;
	}
	
	public GenericExclusiveSelectionPanel getSelectionPanel() {
		return selectionPanel;
	}
	
	public ArrayList<UIBuilder> getClasses() {
		return classes;
	}
	
	public void addClass(UIBuilder newClass) {
		classes.add(newClass);
		
		selectionPanel = new GenericExclusiveSelectionPanel(StringConstants.CLASS, classes);
		
		notifyListeners(selectionPanel);
	}

	@Override
	public void registerListener(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public void update(Object element) {
		addClass((UIBuilder)element);
	}

	@Override
	public void notifyListeners(Object element) {
		for (IListener listener : listeners) {
			listener.update(element);
		}		
	}
}

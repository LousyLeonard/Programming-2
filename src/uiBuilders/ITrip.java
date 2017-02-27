package uiBuilders;

import core.IAddTreeDialog;
import core.IUIBuilderCreator;
import core.ui.DialogBuilder;

/**
 * A definition of the behaviours of Trips.
 * This class is loaded by the CustomClassLoader and as such should implement
 * a no arguments constructor.
 * 
 * @author Lawrence
 */
public interface ITrip extends IUIBuilderCreator{

	/**
	 * Returns a DialogBuilder representing the add dialog of the requested new trip instance.
	 * 
	 * @param addable - The element to add to.
	 * @return DialogBuilder representing the add dialog of the requested new trip instance
	 */
	public DialogBuilder getAddDialog(IAddTreeDialog addable);

}

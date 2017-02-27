/**
 * 
 */
package core;

import core.ui.DialogBuilder;

/**
 * An interface for instantiating a DialogBuilder creator. It's main purpose is
 * to get around the serialisation of the GUI by allowing storage of a GUI
 * generator in the required area.
 * 
 * @author Lawrence
 */
public interface IDialogCreator {
	
	/**
	 * Get a new instance of the requested DialogBuilder.
	 * 
	 * @param addable - The object that is being added to.
	 * @return The DialogBuilder which will specify the attributes
	 * and type of the new element.
	 */
	public DialogBuilder getNewInstance(IAddDialog addable);

}

package core;

import core.ui.DialogBuilder;

/**
*
* Interface to represent whether a class is able to have elements
* added to it using an addition dialog.
*
* @author Lawrence
*/
public interface IAddDialog<T> {

	/**
	 * Add an entry to the structure.
	 * 
	 * @param entry - The value to add.
	 * @throws NotUniqueEntryException when the values already exists.
	 */
	public void addEntry(T entry) throws NotUniqueEntryException;
	
	/**
	 * Register a add dialog provider with the structure. This is used
	 * to add new entries into the structure.
	 * 
	 * @param addDialogCreator - The provider of the addition dialog.
	 */
	public void registerAddDialog(IDialogCreator addDialogCreator);

	/**
	 * Returns an instance of the add dialog, for adding in a new entry. 
	 * 
	 * @return DailogBuilder - A dialog that can be used to specify parameters of the new entry.
	 * @throws NoDialogRegisteredException - A dialog has not been registered yet.
	 */
	public DialogBuilder getAddDialog() throws NoDialogRegisteredException;
}

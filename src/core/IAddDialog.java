package core;

import core.ui.DialogBuilder;

/**
*
* @author Lawrence
*/
public interface IAddDialog<T> {

	public void addEntry(T entry) throws NotUniqueEntryException;
	
	public void registerAddDialog(IDialogCreator addDialogCreator);

	public DialogBuilder getAddDialog() throws NoDialogRegisteredException;
}

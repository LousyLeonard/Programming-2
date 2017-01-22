package core;

import core.ui.DialogBuilder;

public interface IAddDialog<T> {

	public void addEntry(T entry) throws NotUniqueEntryException;
	
	public void registerAddDialog(DialogBuilder addDialog);

	public DialogBuilder getAddDialog() throws NoDialogRegisteredException;
}

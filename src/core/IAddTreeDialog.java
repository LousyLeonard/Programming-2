package core;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import core.ui.DialogBuilder;

public interface IAddTreeDialog<T> extends IAddDialog<T> {

	public void addEntry(DefaultMutableTreeNode parent, T entry, boolean visible) throws NotUniqueEntryException;
	
	public DefaultMutableTreeNode getFolder(String folderName);
	
	public ArrayList<String> getTopLevelElements();

	public DialogBuilder getAddDialogForString(String element);

}

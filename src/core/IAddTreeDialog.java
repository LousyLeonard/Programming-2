package core;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import core.ui.DialogBuilder;

/**
*
* @author Lawrence
*/
public interface IAddTreeDialog<T> extends IAddDialog<T> {

	public void addEntry(DefaultMutableTreeNode parent, T entry, boolean visible) throws NotUniqueEntryException;
	
	public DefaultMutableTreeNode getFolder(String folderName);
	
	public ArrayList<String> getTopLevelElements();

	public DialogBuilder getAddDialogForString(String element);
	
	public void addFolderEntry(String folderName, IDialogCreator dialog);

}

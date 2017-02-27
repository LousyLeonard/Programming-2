package core;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import core.ui.DialogBuilder;

/**
*
* Interface to represent whether a class is able to have elements
* added to it using an addition dialog. This is a tree variant.
*
* @author Lawrence
*/
public interface IAddTreeDialog<T> extends IAddDialog<T> {

	/**
	 * Add an entry to the structure in the specified place.
 	 *
	 * @param parent - The parent node of the new element.
	 * @param entry - The value of the new element.
	 * @param visible - Whether the new element should be visible immediately or not.
	 * @throws NotUniqueEntryException when the values already exists.
	 */
	public void addEntry(DefaultMutableTreeNode parent, T entry, boolean visible) throws NotUniqueEntryException;
	
	/**
	 * Return a top level tree node by name.
	 * 
	 * @param folderName - The name of the tree node.
	 * @return the tree node of the requested name
	 */
	public DefaultMutableTreeNode getFolder(String folderName);
	
	/**
	 * Get all top level nodes.
	 * 
	 * @return all top level nodes.
	 */
	public ArrayList<String> getTopLevelElements();

	/**
	 * Get the add dialog of a specified top level node's name.
	 * 
	 * @param element - The name of the node.
	 * @return an instance of the addition dialog to specific input parameters of the new element.
	 */
	public DialogBuilder getAddDialogForString(String element);
	
	/**
	 * Add an additional top level entry.
	 * 
	 * @param folderName - The name of the new entry.
	 * @param dialog - The addition dialog provider of the new folder structure.
	 */
	public void addFolderEntry(String folderName, IDialogCreator dialog);

}

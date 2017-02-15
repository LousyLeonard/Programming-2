package core;

import javax.swing.tree.DefaultMutableTreeNode;

public interface IAddTreeDialog<T> extends IAddDialog<T> {

	public void addEntry(DefaultMutableTreeNode parent, T entry, boolean visible) throws NotUniqueEntryException;
	
	public DefaultMutableTreeNode getFolder(String folderName);

}

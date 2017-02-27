package core;

import java.io.Serializable;
import java.util.ArrayList;

import core.ui.DialogBuilder;
import core.ui.UIBuilderPanel;

/**
* Building block class that for a type of primary key will populate
* a UI panel for a list of elements of the given type, represented as a table. 
* Additional columns can be added as Column Plugins and additional information
* labels may be added as Label Plugins to represent static information.
* A DialogBuilder creator is registered here which should allow addition
* of a new element of the defined type of primary key.
*
* @author Lawrence
* 
* @param <T> - The primary key type.
*/
public class UIBuilder<T> implements IAddDialog<T>, Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -3207482223745481848L;

	// The title of the UIBuilder
	private String title;

	private ArrayList<T> primaryKeyList;
	private String primaryKeyTitle;
	
	private ArrayList<IColumnPlugin<T>> columnPlugins;
	private ArrayList<ILabelPlugin> labelPlugins;

	/**
	 * A Generated Panel to represent this UIBuilder in a GUI form.
	 */
	private transient UIBuilderPanel<T> panel;
	
	private IDialogCreator addDialogCreator;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param title - The title of this UIBuilder instance.
	 * @param primaryKeyTitle - The title of the Column of Primary Keys.
	 */
	public UIBuilder(String title, String primaryKeyTitle) {
		this.title = title;
		this.primaryKeyTitle = primaryKeyTitle;
		
		primaryKeyList = new ArrayList<T>();
		columnPlugins = new ArrayList<IColumnPlugin<T>>();
		labelPlugins = new ArrayList<ILabelPlugin>();
		
		panel = new UIBuilderPanel<T>(this);
	}
	
	/**
	 * Get the Title of the primary key column.
	 * 
	 * @return the title of the primary key list.
	 */
	public String getPrimaryKeyListTitle() {
		return primaryKeyTitle;
	}
	
	/**
	 * Add a Column to the table.
	 * 
	 * @param plugin - The Column plugin to add.
	 */
	public void addColumnPlugin(IColumnPlugin<T> plugin) {
		columnPlugins.add(plugin);
		updatePanel();
	}
	
	/**
	 * Get all the rows of the table.
	 * 
	 * @return - A list containing the main entry of all the rows in the table.
	 */
	public ArrayList<T> getPrimaryKeyList() {
		return primaryKeyList;
	}
	
	/**
	 * Get a list of all the Columns of the table.
	 * 
	 * @return - a list of all the Columns of the table.
	 */
	public ArrayList<IColumnPlugin<T>> getColumnPlugins() {
		return columnPlugins;
	}
	
	/**
	 * Get a list of all the labels on the panel.
	 * 
	 * @return - a list of all the labels on the panel.
	 */
	public ArrayList<ILabelPlugin> getLabelPlugins() {
		return labelPlugins;
	}
	
	/**
	 * Add a label to the table.
	 * 
	 * @param plugin - The label plugin to add.
	 */
	public void addLabelPlugin(ILabelPlugin plugin) {
		labelPlugins.add(plugin);
		updatePanel();
	}

	/* (non-Javadoc)
	 * @see core.IAddDialog#addEntry(java.lang.Object)
	 */
	@Override
	public void addEntry(T newEntry) throws NotUniqueEntryException {
		primaryKeyList.add(newEntry);
		for(IColumnPlugin<T> plugin : columnPlugins) {
			plugin.put(newEntry, plugin.getDefaultValue());
		}
		panel.refreshModel();
	}
	
	/**
	 * Remove a primary key value and all it's corresponding values from
	 * the table.
	 * 
	 * @param i - The index of the entry.
	 */
	public void removeEntry(int i) {
		primaryKeyList.remove(i);
		for(IColumnPlugin<T> plugin : columnPlugins) {
			plugin.remove(i);
		}
		panel.refreshModel();
	}
	
	/**
	 * Get all the rows of the table as strings.
	 * 
	 * @return - A list containing the main entry of all the rows in the table as strings.
	 */
	public ArrayList<String> getPrimaryKeyStringList() {
		ArrayList<String> primaryKeyStringList = new ArrayList<String>();
		for(T value : primaryKeyList) {
			primaryKeyStringList.add(value.toString());
		}
		return primaryKeyStringList;
	}
	
	/**
	 * Get the title of the UIBuilder.
	 * 
	 * @return - The title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the UIBuilder.
	 * 
	 * @param title - The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getTitle();
	}
	
	/**
	 * Get the UIBuilder generated GUI panel from this UIBuilder instance.
	 * 
	 * @return - The generated UIBuilder GUI panel.
	 */
	public UIBuilderPanel<T> getPanel() {
		if (panel == null) {
			updatePanel();
		}
		return panel;
	}
	
	/**
	 * Generate a new panel for this UIBuilder instance.
	 */
	private void updatePanel() {
		panel = new UIBuilderPanel<T>(this);
	}
	
	/* (non-Javadoc)
	 * @see core.IAddDialog#registerAddDialog(core.IDialogCreator)
	 */
	public void registerAddDialog(IDialogCreator addDialogCreator) {
		this.addDialogCreator = addDialogCreator;
	}

	/* (non-Javadoc)
	 * @see core.IAddDialog#getAddDialog()
	 */
	public DialogBuilder getAddDialog() throws NoDialogRegisteredException {
		if(addDialogCreator == null) {
			throw new NoDialogRegisteredException();
		}
		return addDialogCreator.getNewInstance(this);
	}
	
}

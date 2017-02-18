package core;

import java.io.Serializable;
import java.util.ArrayList;

import core.ui.DialogBuilder;
import core.ui.UIBuilderPanel;

/**
*
* @author Lawrence
*/
public class UIBuilder<T> implements IAddDialog<T>, Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -3207482223745481848L;

	private String title;

	private ArrayList<T> primaryKeyList;
	private String primaryKeyTitle;
	
	private ArrayList<IColumnPlugin<T>> columnPlugins;
	private ArrayList<ILabelPlugin> labelPlugins;

	private transient UIBuilderPanel<T> panel;
	private transient DialogBuilder addDialog;

	public UIBuilder(String title, String primaryKeyTitle) {
		this.title = title;
		this.primaryKeyTitle = primaryKeyTitle;
		
		primaryKeyList = new ArrayList<T>();
		columnPlugins = new ArrayList<IColumnPlugin<T>>();
		labelPlugins = new ArrayList<ILabelPlugin>();
		
		panel = new UIBuilderPanel<T>(this);
	}
	
	public String getPrimaryKeyListTitle() {
		return primaryKeyTitle;
	}
	
	public void addPlugin(IColumnPlugin<T> plugin) {
		columnPlugins.add(plugin);
		updatePanel();
	}
	
	public ArrayList<T> getPrimaryKeyList() {
		return primaryKeyList;
	}
	
	public ArrayList<IColumnPlugin<T>> getPlugins() {
		return columnPlugins;
	}
	
	public ArrayList<ILabelPlugin> getLabelPlugins() {
		return labelPlugins;
	}
	
	public void addLabelPlugin(ILabelPlugin plugin) {
		labelPlugins.add(plugin);
		updatePanel();
	}
	
	public void addEntry(T newEntry) throws NotUniqueEntryException {
		primaryKeyList.add(newEntry);
		for(IColumnPlugin<T> plugin : columnPlugins) {
			plugin.put(newEntry, plugin.getDefaultValue());
		}
		panel.refreshModel();
	}
	
	public void removeEntry(int i) {
		primaryKeyList.remove(i);
		for(IColumnPlugin<T> plugin : columnPlugins) {
			plugin.remove(i);
		}
		panel.refreshModel();
	}
	
	public ArrayList<String> getPrimaryKeyStringList() {
		ArrayList<String> primaryKeyStringList = new ArrayList<String>();
		for(T value : primaryKeyList) {
			primaryKeyStringList.add(value.toString());
		}
		return primaryKeyStringList;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}
	
	public UIBuilderPanel<T> getPanel() {
		if (panel == null) {
			updatePanel();
		}
		return panel;
	}
	
	private void updatePanel() {
		panel = new UIBuilderPanel<T>(this);
	}
	
	public void registerAddDialog(DialogBuilder addDialog) {
		this.addDialog = addDialog;
	}

	public DialogBuilder getAddDialog() throws NoDialogRegisteredException {
		if(addDialog == null) {
			throw new NoDialogRegisteredException();
		}
		return addDialog;
	}
	
}

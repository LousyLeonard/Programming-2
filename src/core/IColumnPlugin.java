package core;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

/**
* Column plugin to allow for a new row to extend the existing table
* on the UIBuilder. A new entry for each of the existing primary key
* list will be generated and set to the default value supplied.
*
* @author Lawrence
*/
public interface IColumnPlugin<T> {
	
	/**
	 * Returns this columns cell editor which defines the edit behaviours.
	 * For example a drop down box of inputs.
	 * 
	 * @return DefaultCellEditor the cell editor.
	 */
	public DefaultCellEditor getCellEditor();
	
	/**
	 * Returns this columns cell renderer which defines the display behaviours.
	 *  
	 * @return DefaultTableCellRenderer the cell renderer.
	 */
	public DefaultTableCellRenderer getCellRenderer();
	
	/**
	 * Returns the title of the plugin.
	 * 
	 * @return String - the title.
	 */
	public String getTitle();
	
	/**
	 * Returns the values held for the given entries in the same order as given.
	 * 
	 * @param primaryKeyList - The given entries.
	 * @return ArrayList<String> - The values held for entries.
	 */
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList);

	/**
	 * Modify or add an existing entry T with the value new Value.
	 * 
	 * @param newValueKey - The entry to change/add.
	 * @param newValue - The value to change/add.
	 */
	public void put(T newValueKey, Object newValue);
	
	/**
	 * Remove an element from the list.
	 * 
	 * @param i - The row index of the element.
	 */
	public void remove(int i);
	
	/**
	 * Get the default value we want cells to initialise to.
	 * 
	 * @return - The default value.
	 */
	public Object getDefaultValue();

}

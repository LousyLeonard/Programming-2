package core;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

/**
*
* @author Lawrence
*/
public interface IColumnPlugin<T> {
	
	public DefaultCellEditor getCellEditor();
	
	public DefaultTableCellRenderer getCellRenderer();
	
	public String getTitle();
	
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList);

	public void put(T newValueKey, Object newValue);
	
	public void remove(int i);
	
	public Object getDefaultValue();

}

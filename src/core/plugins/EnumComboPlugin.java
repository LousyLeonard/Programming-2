package core.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

import core.IColumnPlugin;
import plugins.MoneyDoubleCellRenderer;

public class EnumComboPlugin<E extends Enum<E>, T> implements IColumnPlugin<T> {
	
	private Map<T, E> values;
	private final Class<E> enumType;
	private String title;
	private E defaultValue;

	/*
	 * Needs the initial student list to populate with values
	 */
	public EnumComboPlugin(Class<E> enumType, E defaultValue, ArrayList<T> students, String title) {
		this.values = new HashMap<T, E>();
		this.enumType = enumType;
		this.title = title;
		this.defaultValue = defaultValue;
		
		for(T student : students) {
			values.put(student, (E) getDefaultValue());
		}
	}
	
	public void put(T newValueKey, Object newValue) {
		values.put(newValueKey, (E) newValue);
	}

	@Override
	public void remove(int i) {
		values.remove(i);
	}

	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}

	@Override
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList) {
		
    	ArrayList<String> arrayBuilder = new ArrayList<String>();
    	for(T value : primaryKeyList) {
    		arrayBuilder.add(values.get(value).toString());
    	}
		
		return arrayBuilder;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	public ArrayList<String> getOptions() {
		ArrayList<String> options = new ArrayList<String>();
		for (E option : enumType.getEnumConstants()) {
			options.add(option.toString());
		}
		return options;
	}

	@Override
	public DefaultCellEditor getCellEditor() {
		return new core.ui.ComboBoxEditor(getOptions());
	}
	
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return null;
	}

}

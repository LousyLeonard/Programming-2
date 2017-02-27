package core.plugins;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

import core.IColumnPlugin;

/**
* Creates a new row on the UIBuilder table that allows for a series of set
* values to be edittable by a combobox.
*
* @author Lawrence
* 
* @param <E> - The Enum of which to restrict the options to.
* @param <T> - The primary key type.
*/
public class EnumComboPlugin<E extends Enum<E>, T> implements IColumnPlugin<T>, Serializable {
	
	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 7793807750956933880L;
	
	/**
	 * A map representing the enum value to the corresponding row.
	 */
	private Map<T, E> values;
	
	/**
	 * The Enum of which to allow.
	 */
	private final Class<E> enumType;
	
	/**
	 * The Column heading.
	 */
	private String title;
	
	private E defaultValue;


	/**
	 * CONSTRUCTOR
	 * 
	 * @param enumType - The enum of which to allow.
	 * @param defaultValue - The default value of the enum.
	 * @param primaryKeySet - The initial primary key list to populate with values.
	 * @param title - The Column heading.
	 */
	public EnumComboPlugin(Class<E> enumType, E defaultValue, ArrayList<T> primaryKeySet, String title) {
		this.values = new HashMap<T, E>();
		this.enumType = enumType;
		this.title = title;
		this.defaultValue = defaultValue;
		
		for(T pKey : primaryKeySet) {
			values.put(pKey, (E) getDefaultValue());
		}
	}
	
	/* (non-Javadoc)
	 * @see core.IColumnPlugin#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(T newValueKey, Object newValue) {
		values.put(newValueKey, (E) newValue);
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#remove(int)
	 */
	@Override
	public void remove(int i) {
		values.remove(i);
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getDefaultValue()
	 */
	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getTableEntries(java.util.ArrayList)
	 */
	@Override
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList) {
		
    	ArrayList<String> arrayBuilder = new ArrayList<String>();
    	for(T value : primaryKeyList) {
    		arrayBuilder.add(values.get(value).toString());
    	}
		
		return arrayBuilder;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the enum values as strings.
	 * 
	 * @return the enum values as strings.
	 */
	public ArrayList<String> getOptions() {
		ArrayList<String> options = new ArrayList<String>();
		for (E option : enumType.getEnumConstants()) {
			options.add(option.toString());
		}
		return options;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getCellEditor()
	 */
	@Override
	public DefaultCellEditor getCellEditor() {
		return new core.plugins.ComboBoxEditor(getOptions());
	}
	
	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getCellRenderer()
	 */
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return null;
	}

}

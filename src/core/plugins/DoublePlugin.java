package core.plugins;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

import core.IColumnPlugin;

/**
* A Plugin to add a single numerical column to a UIBuilder table with
* the given column heading.
* 
* @author Lawrence
* 
* @param <T> - The primary key type.
*/
public class DoublePlugin<T> implements IColumnPlugin<T>, Serializable {
	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -2180477354906102067L;

	private static Double DEFAULT_VALUE = 0.0;
	
	/**
	 * A map representing the double value to the corresponding row.
	 */
	private Map<T, Double> payments;
	
	/**
	 * The column heading.
	 */
	private String title;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param primaryKeySet - The initial primary key list to populate with values.
	 * @param title - The Column heading.
	 */
	public DoublePlugin(ArrayList<T> primaryKeySet, String title) {
		this.payments = new HashMap<T, Double>();
		this.title = title;
		
		for(T value : primaryKeySet) {
			payments.put(value, (Double) getDefaultValue());
		}
	}
	
	/* (non-Javadoc)
	 * @see core.IColumnPlugin#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(T newValue, Object value) {
		payments.put(newValue, (Double) value);
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#remove(int)
	 */
	@Override
	public void remove(int i) {
		payments.remove(i);
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getDefaultValue()
	 */
	@Override
	public Object getDefaultValue() {
		return DEFAULT_VALUE;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getTableEntries(java.util.ArrayList)
	 */
	@Override
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList) {
		
    	ArrayList<String> arrayBuilder = new ArrayList<String>();
    	for(T value : primaryKeyList) {
    		arrayBuilder.add(Double.toString(payments.get(value)));
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
	
	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getCellEditor()
	 */
	@Override
	public DefaultCellEditor getCellEditor() {
		return null;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getCellRenderer()
	 */
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return null;
	}
}
 
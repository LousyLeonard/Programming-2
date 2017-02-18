package core.plugins;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

import core.IColumnPlugin;
import plugins.MoneyDoubleCellRenderer;

/**
*
* @author Lawrence
*/
public class DoublePlugin<T> implements IColumnPlugin<T>, Serializable {
	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -2180477354906102067L;

	private static Double DEFAULT_VALUE = 0.0;
		
	private Map<T, Double> payments;
	private String title;

	/*
	 * Needs the initial student list to populate with values
	 */
	public DoublePlugin(ArrayList<T> primaryKeySet, String title) {
		this.payments = new HashMap<T, Double>();
		this.title = title;
		
		for(T value : primaryKeySet) {
			payments.put(value, (Double) getDefaultValue());
		}
	}
	
	@Override
	public void put(T newValue, Object value) {
		payments.put(newValue, (Double) value);
	}

	@Override
	public void remove(int i) {
		payments.remove(i);
	}

	@Override
	public Object getDefaultValue() {
		return DEFAULT_VALUE;
	}

	@Override
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList) {
		
    	ArrayList<String> arrayBuilder = new ArrayList<String>();
    	for(T value : primaryKeyList) {
    		arrayBuilder.add(Double.toString(payments.get(value)));
    	}
		
		return arrayBuilder;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public DefaultCellEditor getCellEditor() {
		return null;
	}

	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return new MoneyDoubleCellRenderer(2);
	}
}
 
package plugins;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

import core.IColumnPlugin;
import tripTracker.StringConstants;
import tripTracker.Student;

/**
*
* @author Lawrence
*/
public class PhoneNumberPlugin<T> implements IColumnPlugin<T>, Serializable {
	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 1394451573541155021L;

	private static String DEFAULT_VALUE = StringConstants.PHONE_NO_NOT_SUPPLIED;

	ArrayList<T> students;
	
	/*
	 * Needs the initial student list to populate with values
	 */
	public PhoneNumberPlugin(ArrayList<T> primaryKeySet) {
		this.students = primaryKeySet;
	}
	
	@Override
	public DefaultCellEditor getCellEditor() {
		// Gives us default
		return null;
	}

	@Override
	public String getTitle() {
		return StringConstants.PHONE_NUMBER;
	}

	@Override
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList) {
		ArrayList<String> phoneNos = new ArrayList<String>();
		for(T entry : students) {
			phoneNos.add(((Student)entry).getPhoneNumber());
		}
		return phoneNos;
	}

	@Override
	public void put(T newValueKey, Object newValue) {
		//Intentionally left blank
	}

	@Override
	public void remove(int i) {
		//Intentionally left blank		
	}

	@Override
	public Object getDefaultValue() {
		return DEFAULT_VALUE;
	}
	
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return null;
	}

}

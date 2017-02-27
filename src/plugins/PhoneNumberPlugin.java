package plugins;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

import core.IColumnPlugin;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;

/**
* PhoneNumber column for representing the phone numbers of students.
* 
* @author Lawrence
*/
public class PhoneNumberPlugin<T> implements IColumnPlugin<T>, Serializable {
	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 1394451573541155021L;

	private static String DEFAULT_VALUE = TripTrackerConstants.PHONE_NO_NOT_SUPPLIED;

	private ArrayList<T> students;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param primaryKeySet - The initial student list to populate with values
	 */
	public PhoneNumberPlugin(ArrayList<T> primaryKeySet) {
		this.students = primaryKeySet;
	}
	
	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getCellEditor()
	 */
	@Override
	public DefaultCellEditor getCellEditor() {
		// Gives us default
		return null;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getTitle()
	 */
	@Override
	public String getTitle() {
		return TripTrackerConstants.PHONE_NUMBER;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getTableEntries(java.util.ArrayList)
	 */
	@Override
	public ArrayList<String> getTableEntries(ArrayList<T> primaryKeyList) {
		ArrayList<String> phoneNos = new ArrayList<String>();
		for(T entry : students) {
			phoneNos.add(((Student)entry).getPhoneNumber());
		}
		return phoneNos;
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(T newValueKey, Object newValue) {
		//Intentionally left blank
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#remove(int)
	 */
	@Override
	public void remove(int i) {
		//Intentionally left blank		
	}

	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getDefaultValue()
	 */
	@Override
	public Object getDefaultValue() {
		return DEFAULT_VALUE;
	}
	
	/* (non-Javadoc)
	 * @see core.IColumnPlugin#getCellRenderer()
	 */
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		// Gives us default
		return null;
	}

}

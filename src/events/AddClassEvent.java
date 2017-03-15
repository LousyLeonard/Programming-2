/**
 * 
 */
package events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import core.IAddDialog;
import core.IYesNoEvent;
import core.InvalidEntryException;
import core.NotUniqueEntryException;
import core.UIBuilder;
import core.ui.DialogBuilder;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;

/**
 * Event to add a Class to a Trip.
 * 
 * @author Lawrence
 */
public class AddClassEvent implements IYesNoEvent, Serializable {
	
	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -7355811889480469666L;
	
	private IAddDialog addable;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param addable - The element to add to.
	 */
	public AddClassEvent(IAddDialog addable) {
		this.addable = addable;
	}

	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		try {
			for (Student student : parse(builder.getEntrys()))
			{
				addable.addEntry(student);	
			}
		} catch (NotUniqueEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "WARNING.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);		
		} catch (InvalidEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "Invalid entry added for field: " + e.getMessage(),
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);				
		}	
	}
	
	/**
	 * Parse the given input.
	 * 
	 * @param entries - The given input.
	 * @return The list of students in the requested class.
	 */
	private ArrayList<Student> parse(Map<String, Object> entries) {
		UIBuilder<Student> studentBuilder = (UIBuilder<Student>) entries.get(TripTrackerConstants.CLASS);
		
		ArrayList<Student> students = studentBuilder.getPrimaryKeyList();
		
		return students;
	}

}

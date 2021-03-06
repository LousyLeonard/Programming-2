package events;

import java.io.Serializable;
import java.util.Map;

import javax.swing.JOptionPane;

import core.IYesNoEvent;
import core.InvalidEntryException;
import core.ui.DialogBuilder;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;
import core.IAddDialog;
import core.NotUniqueEntryException;

/**
 * Event to add a Student to a Class.
 * 
 * @author Lawrence
 */
public class AddStudentEvent implements IYesNoEvent, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1003884309706649425L;
	
	private IAddDialog addable;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param addable - The element to add to.
	 */
	public AddStudentEvent(IAddDialog addable) {
		this.addable = addable;
	}
	
	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		try {
			addable.addEntry(parse(builder.getEntrys()));	
		} catch (NotUniqueEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "WARNING.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);		
		} catch (InvalidEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "Invalid entry added for field: " + e.getPanelName(),
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);				
		}	
	}

	/**
	 * Parse the given input.
	 * 
	 * @param entries - The given input.
	 * @return The students represented by the input.
	 */
	private Student parse(Map<String, Object> entries) {
		return new Student(
				(String)entries.get(TripTrackerConstants.FIRST_NAME), 
				(String)entries.get(TripTrackerConstants.SECOND_NAME),
				(String)entries.get(TripTrackerConstants.PHONE_NUMBER));
	}
}

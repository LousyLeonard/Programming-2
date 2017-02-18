package events;

import java.util.Map;

import javax.swing.JOptionPane;

import core.IYesNoEvent;
import core.ui.DialogBuilder;
import tripTracker.StringConstants;
import tripTracker.Student;
import core.IAddDialog;
import core.NotUniqueEntryException;

/**
*
* @author Lawrence
*/
public class AddStudentEvent implements IYesNoEvent {

	private IAddDialog addable;
	
	public AddStudentEvent(IAddDialog addable) {
		this.addable = addable;
	}
	
	@Override
	public void doEvent(DialogBuilder builder) {
		try {
			addable.addEntry(parse(builder.getEntrys()));	
		} catch (NotUniqueEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "WARNING.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);		}
	}

	private Student parse(Map<String, Object> entries) {
		return new Student(
				(String)entries.get(StringConstants.FIRST_NAME), 
				(String)entries.get(StringConstants.SECOND_NAME),
				(String)entries.get(StringConstants.PHONE_NUMBER));
	}
}

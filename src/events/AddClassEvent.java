/**
 * 
 */
package events;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import core.IAddDialog;
import core.IYesNoEvent;
import core.NotUniqueEntryException;
import core.UIBuilder;
import core.ui.DialogBuilder;
import tripTracker.StringConstants;
import tripTracker.Student;

/**
 * @author Lawrence
 *
 */
public class AddClassEvent implements IYesNoEvent {
	
	private IAddDialog addable;

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
		}
	}
	
	private ArrayList<Student> parse(Map<String, Object> entries) {
		UIBuilder<Student> studentBuilder = (UIBuilder<Student>) entries.get(StringConstants.CLASS);
		
		ArrayList<Student> students = studentBuilder.getPrimaryKeyList();
		
		return students;
	}

}

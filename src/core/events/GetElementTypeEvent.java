package core.events;

import java.util.Map;

import javax.swing.JOptionPane;

import core.CoreConstants;
import core.IAddTreeDialog;
import core.IYesNoEvent;
import core.InvalidEntryException;
import core.ui.DialogBuilder;

/**
* Creates a DialogBuilder for a selected top level element.
*
* @author Lawrence
*/
public class GetElementTypeEvent implements IYesNoEvent {
	
	/**
	 * The IAddTreeDialog for the new element to be added to.
	 */
	private IAddTreeDialog addable;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param addable - The IAddTreeDialog for the new element to be added to.
	 */
	public GetElementTypeEvent(IAddTreeDialog addable) {
		this.addable = addable;
	}

	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		try {
			Map<String, Object> entries = builder.getEntrys();
			String choice = (String)entries.get(CoreConstants.ELEMENT_TYPE);

			// Create new dialog for the choice
			for (Object element : addable.getTopLevelElements()) {
				if(choice.equals((String)element)) {
					DialogBuilder dialog = addable.getAddDialogForString(choice);
					dialog.setVisible(true);
					
					// On success kill the dialog
					builder.dispose();
				}
			}
		} catch (InvalidEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "Invalid entry added for field: " + e.getPanelName(),
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);				
		}		
	}

}

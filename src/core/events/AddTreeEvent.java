package core.events;

import java.util.Map;

import javax.swing.JOptionPane;

import core.IAddTreeDialog;
import core.IUIBuilderCreator;
import core.IYesNoEvent;
import core.NotUniqueEntryException;
import core.UIBuilder;
import core.ui.DialogBuilder;

/**
*
* @author Lawrence
*/
public class AddTreeEvent implements IYesNoEvent {
	
	/**
	 * The IAddTreeDialog for the new element to be added to.
	 */
	private IAddTreeDialog addable;
	
	/**
	 * The GUI generator to generate the GUI panel to be shown for this
	 * element.
	 */
	private IUIBuilderCreator factory;
	
	/**
	 * The top level element of the IAddTreeDialog the new entry goes under.
	 */
	private String container;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param addable - The IAddTreeDialog for the new element to be added to.
	 * @param factory - The GUI generator to generate the GUI panel to be shown for this
	 * element.
	 * @param container - The top level element of the IAddTreeDialog the new entry goes under.
	 */
	public AddTreeEvent(IAddTreeDialog addable, IUIBuilderCreator factory, String container) {
		this.addable = addable;
		this.factory = factory;
		this.container = container;
	}
	
	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		try {
			addable.addEntry(addable.getFolder(container), parse(builder.getEntrys()), true);
			
			// Get rid of the entry dialog.
			builder.dispose();
		} catch (NotUniqueEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "An entry by this name already exists.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);		
		}
	}
	
	/**
	 * Parse the given data.
	 * 
	 * @param entries - The given data.
	 * @return the UIBuilder representing the given data.
	 */
	private UIBuilder parse(Map<String, Object> entries) {
		return this.factory.getNewInstance(entries);
	}

}

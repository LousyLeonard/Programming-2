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
	
	private IAddTreeDialog addable;
	private IUIBuilderCreator factory;
	private String container;

	public AddTreeEvent(IAddTreeDialog addable, IUIBuilderCreator factory, String container) {
		this.addable = addable;
		this.factory = factory;
		this.container = container;
	}
	
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
	
	private UIBuilder parse(Map<String, Object> entries) {
		return this.factory.getNewInstance(entries);
	}

}

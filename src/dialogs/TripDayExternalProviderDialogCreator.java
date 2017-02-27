/**
 * 
 */
package dialogs;

import java.io.Serializable;
import java.util.ArrayList;

import core.CustomClassLoader;
import core.IAddDialog;
import core.IAddTreeDialog;
import core.IDialogCreator;
import core.IEntryPanelProvider;
import core.events.AddTreeEvent;
import core.events.CloseWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericNumberEntryPanel;
import core.ui.entrypanels.GenericStringEntryPanel;
import core.ui.entrypanels.SmartExclusiveSelectionPanel;
import core.util.ArrayCastingUtils;
import transports.ITransport;
import tripTracker.TripTrackerConstants;
import uiBuilders.DayTripExtCreator;

/**
 * A Dialog creator for adding a trip to an IAddTreeDialog.
 *  
 * @author Lawrence
 *
 */
public class TripDayExternalProviderDialogCreator implements IDialogCreator, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -3204299114982115631L;
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getTripDayExternalProviderDialog((IAddTreeDialog)addable);
	}
	
	/**
	 * Create a new trip dialog.
	 * 
	 * @param addable - The element to add to.
	 * @return the new trip DialogBuilder.
	 */
	private static DialogBuilder getTripDayExternalProviderDialog(IAddTreeDialog addable) {
		GenericStringEntryPanel title = new GenericStringEntryPanel(TripTrackerConstants.TITLE);
		GenericNumberEntryPanel entryFee = new GenericNumberEntryPanel(TripTrackerConstants.ENTRY_FEE);

		CustomClassLoader<ITransport> transportLoader = new CustomClassLoader<ITransport>(ITransport.class);
		ArrayList<ITransport> transports = transportLoader.getElements();		
		ArrayList<IEntryPanelProvider> transportPanels = ArrayCastingUtils.convertArray(IEntryPanelProvider.class, transports);	
		SmartExclusiveSelectionPanel transport = new SmartExclusiveSelectionPanel(TripTrackerConstants.TRANSPORT, transportPanels);
	
		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.NEW_TRIP);

		builder.addPanel(title);
		builder.addPanel(entryFee);
		builder.addPanel(transport);
		
		builder.registerNoEvent(new CloseWindowEvent());
		builder.registerYesEvent(new AddTreeEvent(addable, new DayTripExtCreator(), TripTrackerConstants.TRIPS));
		
		return builder;
	}

}

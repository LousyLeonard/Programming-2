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
import uiBuilders.DayTripTeacherCreator;
import venueBookings.IVenueBooking;

/**
 * A Dialog creator for adding a trip to an IAddTreeDialog.
 * 
 * @author Lawrence
 */
public class TripDayTeacherOrganisedDialogCreator implements IDialogCreator, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1786204385327614961L;

	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getTripDayTeacherOrganisedDialog((IAddTreeDialog)addable);
	}
	
	/**
	 * Create a new trip dialog.
	 * 
	 * @param addable - The element to add to.
	 * @return the new trip DialogBuilder.
	 */
	private static DialogBuilder getTripDayTeacherOrganisedDialog(IAddTreeDialog addable) {
		GenericStringEntryPanel title = new GenericStringEntryPanel(TripTrackerConstants.TITLE);
		GenericNumberEntryPanel entryFee = new GenericNumberEntryPanel(TripTrackerConstants.ENTRY_FEE);
		
		CustomClassLoader<ITransport> transportLoader = new CustomClassLoader<ITransport>(ITransport.class);
		ArrayList<ITransport> transports = transportLoader.getElements();		
		ArrayList<IEntryPanelProvider> transportPanels = ArrayCastingUtils.convertArray(IEntryPanelProvider.class, transports);	
		SmartExclusiveSelectionPanel transport = new SmartExclusiveSelectionPanel(TripTrackerConstants.TRANSPORT, transportPanels);

		CustomClassLoader<IVenueBooking> venueLoader = new CustomClassLoader<IVenueBooking>(IVenueBooking.class);
		ArrayList<IVenueBooking> venues = venueLoader.getElements();		
		ArrayList<IEntryPanelProvider> venuePanels = ArrayCastingUtils.convertArray(IEntryPanelProvider.class, venues);	
		SmartExclusiveSelectionPanel venueBooking = new SmartExclusiveSelectionPanel(TripTrackerConstants.VENUE_BOOKING, venuePanels);
		
		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.NEW_TRIP);

		builder.addPanel(title);
		builder.addPanel(entryFee);
		builder.addPanel(transport);
		builder.addPanel(venueBooking);
		
		builder.registerNoEvent(new CloseWindowEvent());
		builder.registerYesEvent(new AddTreeEvent(addable, new DayTripTeacherCreator(), TripTrackerConstants.TRIPS));
		
		return builder;
	}

}
